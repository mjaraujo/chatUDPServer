/*
 * UDPServidor.java
 * Created on 1 de Mar�o de 2002, 21:29
 * @author denisls
 */
package com.mjasistemas.chatclientudp.server;

import com.mjasistemas.chatclientudp.controller.UsuarioController;
import com.mjasistemas.chatclientudp.dao.Pessoa.PessoaDao;
import com.mjasistemas.chatclientudp.dao.Pessoa.SalaDao;
import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.StatusLoginPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import java.net.*;
import java.io.*;
import java.util.List;

public class UDPServidor implements Runnable {

    private String resposta;
    private DatagramSocket aSoquete = null;

    private RetornoEnum tratarRequisicao(DatagramPacket requisicao) {
        int sala = 0;
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
                retornoLogin = pessoaLogada == null ? StatusLoginPessoaEnum.NAO_EXISTE : StatusLoginPessoaEnum.OK;
                retornoLogin = pessoaLogada.getId() < 0 ? StatusLoginPessoaEnum.SENHA_INVALIDA : StatusLoginPessoaEnum.OK;
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
                new PessoaDao().update(pessoaEntrada);
                return RetornoEnum.SOLICITACAO_PROCESSADA;
            case 3://solicitar logados na sala
                //verificar situação do usuário ex: se está banido
                apelido = tmp.substring(3, 14).trim(); //apelido
                sala = Integer.parseInt(tmp.substring(14, 19));
                resposta += "030";
                List<Pessoa> logadosSala = new UsuarioController().logadosSala(apelido, sala);
                resposta += String.format("%02d", logadosSala.size());
                for (Pessoa p: logadosSala) {
                    resposta+=String.format("%05d", p.getId());
                    resposta+=String.format("%12s", p.getNickName());
                }
                return RetornoEnum.SOLICITACAO_PROCESSADA;

        }
        return RetornoEnum.ENTRADA_NAO_CADASTRADO;
    }

    public UDPServidor() {
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

    @Override
    public void run() {
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
