/*
 * UDPServidor.java
 * Created on 1 de Mar�o de 2002, 21:29
 * @author denisls
 */
package com.mjasistemas.chatclientudp.server;

import com.mjasistemas.chatclientudp.controller.MensagemController;
import com.mjasistemas.chatclientudp.controller.UsuarioController;
import com.mjasistemas.chatclientudp.dao.Pessoa.PessoaDao;
import com.mjasistemas.chatclientudp.dao.Pessoa.SalaDao;
import com.mjasistemas.chatclientudp.dao.mensagem.MensagemDao;
import com.mjasistemas.chatclientudp.model.Mensagem;
import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.StatusLoginPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import java.net.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServidor implements Runnable {

    private List<Pessoa> comunicantes;
    private String resposta;
    private DatagramSocket aSoquete = null;

    private RetornoEnum tratarRequisicao(DatagramPacket requisicao) {
        int sala = 0;
        Timestamp timestamp;
        resposta = "";
        int tamResp = requisicao.getLength();
        String tmp = new String(requisicao.getData(), 0, tamResp);

        int tipo = Integer.parseInt(tmp.toString().substring(0, 2));
        String apelido = "";
        switch (tipo) {
            case 0://logar no sistema
                //verificar situação do usuário ex: se está banido
                apelido = tmp.substring(2, 14).trim(); //apelido
                String senha = tmp.substring(14, 34).trim(); //senha
                Pessoa pessoaLogada = new UsuarioController().permitirLogin(apelido, senha);
                StatusLoginPessoaEnum retornoLogin = StatusLoginPessoaEnum.OK;
                if (pessoaLogada == null) {
                    retornoLogin = StatusLoginPessoaEnum.NAO_EXISTE;
                } else if (pessoaLogada.getId() < 0) {
                    retornoLogin = StatusLoginPessoaEnum.SENHA_INVALIDA;
                }
                //pareiiiiii
                resposta = "00";
                switch (retornoLogin) {
                    case OK:
                        sala = Integer.parseInt(tmp.substring(1, 6).trim());
                        resposta += "0";
                        resposta += String.format("%05d", pessoaLogada.getId());
                        resposta += String.format("%12s", pessoaLogada.getNickName());
                        switch (pessoaLogada.getTipo()) {
                            case ADMINISTRADOR:
                                resposta += "0";
                                break;
                            case MODERADOR:
                                resposta += "1";
                                break;
                            case USUARIO:
                                resposta += "2";
                                break;
                        }
                        return RetornoEnum.SOLICITACAO_PROCESSADA;
                    case NAO_EXISTE:
                        resposta += "1";
                        return RetornoEnum.SOLICITACAO_PROCESSADA;
                    case SENHA_INVALIDA:
                        resposta += "2";
                        return RetornoEnum.SOLICITACAO_PROCESSADA;
                }

                break;

            case 1://solicitar salas
                //verificar situação do usuário ex: se está banido
                apelido = tmp.substring(2, 14).trim(); //apelido
                List<Sala> allAbertas = new SalaDao().getAllAbertas();
                resposta += "010";
                resposta += String.format("%02d", allAbertas.size());
                for (Sala s : allAbertas) {
                    resposta += String.format("%05d", s.getId());
                    resposta += String.format("%40s", s.getNome());
                    resposta += String.format("%03d", s.getCapacidade());
                }
                resposta += " ";
                return RetornoEnum.SOLICITACAO_PROCESSADA;
            case 2://solicitar entrar na sala
                //verificar situação do usuário ex: se está banido
                sala = Integer.parseInt(tmp.substring(4, 7));
                apelido = tmp.substring(8, 19).trim(); //apelido
                resposta += "020";
                resposta += String.format("%12s", apelido);
                Pessoa pessoaEntrada = new UsuarioController().acessarSala(apelido, sala);
                timestamp = new Timestamp(System.currentTimeMillis());
                pessoaEntrada.setAlive(timestamp);
                comunicantes.add(pessoaEntrada);
                new PessoaDao().update(pessoaEntrada);
                return RetornoEnum.SOLICITACAO_PROCESSADA;
            case 3://solicitar logados na sala
                //verificar situação do usuário ex: se está banido
                apelido = tmp.substring(3, 14).trim(); //apelido
                sala = Integer.parseInt(tmp.substring(14, 19));
                resposta += "030";
                List<Pessoa> logadosSala = new UsuarioController().logadosSala(apelido, sala);
                resposta += String.format("%02d", logadosSala.size());
                for (Pessoa p : logadosSala) {
                    resposta += String.format("%05d", p.getId());
                    resposta += String.format("%12s", p.getNickName());
                }

                return RetornoEnum.SOLICITACAO_PROCESSADA;
            case 4://solicitar envio de mensagem
                sala = Integer.parseInt(tmp.substring(3, 8).trim()); //apelido
                String from = tmp.substring(8, 20).trim();
                String to = tmp.substring(20, 32).trim();
                String mensagem = tmp.substring(32, 231).trim();
                Mensagem msgChat = new Mensagem();
                msgChat.setRemetente(new PessoaDao().getByNickName(from).getId());
                msgChat.setDestinatario(new PessoaDao().getByNickName(to).getId());
                msgChat.setConteudo(mensagem);
                msgChat.setSala(new SalaDao().getById(sala));

                timestamp = new Timestamp(System.currentTimeMillis());
                msgChat.setTimestamp(timestamp);
                new MensagemDao().save(msgChat);
                resposta += "040";

                return RetornoEnum.SOLICITACAO_PROCESSADA;
            case 5://solicitar envio novas mensagens                
                sala = Integer.parseInt(tmp.substring(2, 7).trim());
                System.out.println(tmp);
                System.out.println(tmp.substring(7, 29));
                timestamp = Timestamp.valueOf(tmp.substring(7, 29));
                System.out.println("hhhhhhhh:" + timestamp.toString());
                resposta += "050";

                List<Mensagem> novasMensagens = new MensagemController().solicitarNovasMensagens(sala, timestamp);
                resposta += String.format("%03d", novasMensagens.size());
                for (Mensagem m : novasMensagens) {
                    String S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(m.getTimestamp());
                    resposta += String.format("%23s", S);
                    resposta += String.format("%12s", new PessoaDao().getById(m.getRemetente()).getNickName());
                    resposta += String.format("%12s", new PessoaDao().getById(m.getDestinatario()).getNickName());
                    resposta += String.format("%200s", m.getConteudo());
                }

                return RetornoEnum.SOLICITACAO_PROCESSADA;
            case 88:
                String usuario = tmp.substring(3, 14).trim();
                timestamp = new Timestamp(System.currentTimeMillis());
                new PessoaDao().getByNickName(usuario).setAlive(timestamp);
                return RetornoEnum.SOLICITACAO_PROCESSADA;
        }
        return RetornoEnum.ENTRADA_NAO_CADASTRADO;
    }

    public UDPServidor() {
        this.comunicantes = new ArrayList<>();
    }

    private void iniciar() {

        try {
            aSoquete = new DatagramSocket(9876);
            while (true) {
                byte[] buffer = new byte[4096];
                DatagramPacket requisicao = new DatagramPacket(buffer, buffer.length);
                aSoquete.receive(requisicao);

                RetornoEnum respostaRequisicao = tratarRequisicao(requisicao);

                if (respostaRequisicao == RetornoEnum.SOLICITACAO_PROCESSADA) {
                    if (resposta.length() == 0) {
                        continue;
                    }
                    buffer = resposta.getBytes();
                    DatagramPacket msgResposta = new DatagramPacket(buffer, resposta.length(), requisicao.getAddress(), requisicao.getPort());
                    aSoquete.send(msgResposta);
                    //return;
                }
                if (respostaRequisicao == RetornoEnum.ERRO_SIZE) {
                    String msgErro = "00";
                    buffer = msgErro.getBytes();
                    DatagramPacket dtResposta = new DatagramPacket(buffer, msgErro.length(), requisicao.getAddress(), requisicao.getPort());
                    aSoquete.send(dtResposta);
                    //return;
                }

            }
        } catch (SocketException e) {
            System.out.println("Soquete: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSoquete != null) {
                //aSoquete.close();
            }
        }
    }

    private void monitoraAlives() {
        do {
            for (Pessoa p : comunicantes) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long diff = (timestamp.getTime() - p.getAlive().getTime()) / 1000;
                System.out.println("DIFERENÇA:" + diff);
                if (diff > 5) {
                    p.setSala(0);
                    new PessoaDao().update(p);
                }
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UDPServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    @Override
    public void run() {
        new Thread(new Runnable() {
            public void run() {
                monitoraAlives();
            }
        }).start();
        iniciar();
    }

    /**
     * @return the resposta
     */
    public String getResposta() {
        return resposta;
    }

    /**
     * @param resposta the resposta to set
     */
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

}
