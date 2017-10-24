/*
 * UDPServidor.java
 * Created on 1 de Mar�o de 2002, 21:29
 * @author denisls
 */
package com.mjasistemas.chatclientudp.server;

import java.net.*;
import java.io.*;

public class UDPServidor implements Runnable {

    private DatagramSocket aSoquete = null;

    private void tratarRequisicao(DatagramPacket requisicao) {
        int tamResp = requisicao.getLength();
        String tmp = new String(requisicao.getData(), 0, tamResp);

        int tipo = Integer.parseInt(tmp.toString().substring(0, 1));
        switch (tipo) {
            case 1://entrar na sala
                //verificar situação do solicitante ex: se está banido
                String apelido = tmp.substring(6, 18);
                System.out.println("apelido: " + apelido.trim());

                break;
        }
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

                tratarRequisicao(requisicao);

                String usuario = System.getProperty("user.name");
                int tamResp = requisicao.getLength();
                String tmp = new String(requisicao.getData(), 0, tamResp) + usuario;

                System.out.println(tmp);
                System.out.println(usuario);
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
