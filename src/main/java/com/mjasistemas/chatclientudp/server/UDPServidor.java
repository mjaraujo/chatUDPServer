/*
 * UDPServidor.java
 * Created on 1 de Mar�o de 2002, 21:29
 * @author denisls
 */
package com.mjasistemas.chatclientudp.server;

import com.mjasistemas.chatclientudp.controller.UsuarioController;
import com.mjasistemas.chatclientudp.model.RetornoEntradaEnum;
import java.net.*;
import java.io.*;

public class UDPServidor implements Runnable {

    private DatagramSocket aSoquete = null;

    private RetornoEntradaEnum tratarRequisicao(DatagramPacket requisicao) {
        int tamResp = requisicao.getLength();
        String tmp = new String(requisicao.getData(), 0, tamResp);

        int tipo = Integer.parseInt(tmp.toString().substring(0, 1));
        switch (tipo) {
            case 1://entrar na sala
                //verificar situação do usuário ex: se está banido
                String apelido = tmp.substring(6, 18).trim(); //apelido
                int sala = Integer.parseInt(tmp.substring(1, 6).trim()); // sala

                boolean acesso = new UsuarioController().permitirAcessoSala(apelido, sala);
                if (acesso) {
                    return RetornoEntradaEnum.OK;
                } else {
                    return RetornoEntradaEnum.BANIDO;
                }
        }
        return RetornoEntradaEnum.NAO_CADASTRADO;
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

                RetornoEntradaEnum respostaRequisicao = tratarRequisicao(requisicao);
                int tamResp = requisicao.getLength();
                String req = new String(requisicao.getData(), 0, tamResp);
                String tmp;
                tmp = "1";
                tmp += String.format("%12s", req.substring(6, 18));
                switch (respostaRequisicao) {
                    case OK:
                        tmp += 0;
                        break;
                    case BANIDO:
                        tmp += 1;
                        break;
                    case NAO_CADASTRADO:
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
                aSoquete.close();
            }
        }
    }

    @Override
    public void run() {
        iniciar();
    }

}
