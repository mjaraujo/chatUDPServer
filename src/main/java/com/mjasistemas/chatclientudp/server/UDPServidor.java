/*
 * UDPServidor.java
 * Created on 1 de Mar�o de 2002, 21:29
 * @author denisls
 */
package com.mjasistemas.chatclientudp.server;

import com.mjasistemas.chatclientudp.controller.UsuarioController;
import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.StatusLoginPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import java.net.*;
import java.io.*;

public class UDPServidor implements Runnable {

    private DatagramSocket aSoquete = null;

    private RetornoEnum tratarRequisicao(DatagramPacket requisicao) {
        int tamResp = requisicao.getLength();
        String tmp = new String(requisicao.getData(), 0, tamResp);

        int tipo = Integer.parseInt(tmp.toString().substring(0, 2));
        switch (tipo) {
            case 0://logar no sistema
                //verificar situação do usuário ex: se está banido
                if (tamResp != 34) {
                    return RetornoEnum.ERRO_SIZE;
                }
                String apelido = tmp.substring(2, 13).trim(); //apelido
                String senha = tmp.substring(14, 33).trim(); //senha
                Pessoa permitirLogin = new UsuarioController().permitirLogin(apelido, senha);
                StatusLoginPessoaEnum retornoLogin = StatusLoginPessoaEnum.OK;
                retornoLogin = permitirLogin== null ? StatusLoginPessoaEnum.NAO_EXISTE : StatusLoginPessoaEnum.OK;
                retornoLogin = permitirLogin.getId() < 0 ? StatusLoginPessoaEnum.SENHA_INVALIDA : StatusLoginPessoaEnum.OK;
                //pareiiiiii
                String resposta = "00";

                switch (retornoLogin) {
                    case OK:
                        resposta += "0";
                        int sala = Integer.parseInt(tmp.substring(1, 6).trim());
                        resposta += String.format("%05d", sala);
                        msg += String.format("%12s", usuario);

                        break;
                    case NAO_EXISTE:
                        resposta += "1";
                        break;
                    case SENHA_INVALIDA:
                        resposta += "2";
                        break;
                }
                break;

            case 1://entrar na sala
                //verificar situação do usuário ex: se está banido
                if (tamResp != 34) {
                    return RetornoEnum.ERRO_SIZE;
                }
                String apelido = tmp.substring(6, 18).trim(); //apelido
                int sala = Integer.parseInt(tmp.substring(1, 6).trim()); // sala

                boolean acesso = new UsuarioController().permitirAcessoSala(apelido, sala);
                if (acesso) {
                    return RetornoEnum.ENTRADA_OK;
                } else {
                    return RetornoEnum.ENTRADA_BANIDO;
                }
                break;

        }
        return RetornoEnum.ENTRADA_NAO_CADASTRADO;
    }

    public UDPServidor() {
    }

    private void iniciar() {

        try {
            aSoquete = new DatagramSocket(9876);
            byte[] buffer = new byte[100];
            while (true) {
                DatagramPacket requisicao = new DatagramPacket(buffer, buffer.length);
                aSoquete.receive(requisicao);

                RetornoEnum respostaRequisicao = tratarRequisicao(requisicao);
                if (respostaRequisicao == RetornoEnum.ERRO_SIZE) {
                    String msgErro = "00";
                    buffer = msgErro.getBytes();
                    DatagramPacket resposta = new DatagramPacket(buffer, msgErro.length(), requisicao.getAddress(), requisicao.getPort());
                    aSoquete.send(resposta);
                    return;
                }
                int tamResp = requisicao.getLength();
                String req = new String(requisicao.getData(), 0, tamResp);
                String tmp;
                tmp = "1";
                tmp += String.format("%12s", req.substring(6, 18));
                switch (respostaRequisicao) {
                    case ENTRADA_OK:
                        tmp += 0;
                        break;
                    case ENTRADA_NAO_CADASTRADO:
                        tmp += 1;
                        break;
                    case ENTRADA_BANIDO:
                        tmp += 2;
                        break;

                }

                String usuario = System.getProperty("user.name");

                buffer = tmp.getBytes();
                DatagramPacket resposta = new DatagramPacket(buffer, tmp.length(), requisicao.getAddress(), requisicao.getPort());
                aSoquete.send(resposta);
            }
        } catch (SocketException e) {
            System.out.println("Soquete: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSoquete != null) {
                //               aSoquete.close();
            }
        }
    }

    @Override
    public void run() {
        iniciar();
    }

}
