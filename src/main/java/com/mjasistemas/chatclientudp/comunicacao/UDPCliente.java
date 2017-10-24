/*
 * UDPCliente.java
 * Created on 1 de Mar�o de 2002, 21:25
 * @author denisls
 */
package com.mjasistemas.chatclientudp.comunicacao;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPCliente implements Runnable {

    private final String ip;
    private final int servidorPorta;
    private DatagramSocket aSoquete;

    public UDPCliente(String ip, int servidorPorta) {
        this.ip = ip;
        this.servidorPorta = servidorPorta;
        try {
            aSoquete = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(UDPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviar(String mensagem) {

        try {
            byte[] m = mensagem.getBytes();
            InetAddress aHost = InetAddress.getByName(/*args[1]*/ip);
            DatagramPacket requisicao = new DatagramPacket(m, mensagem.length(), aHost, servidorPorta);
            aSoquete.send(requisicao);

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

    private void escutar() {
        byte[] buffer = new byte[255];
        DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
        try {
            aSoquete.receive(resposta);
        } catch (IOException ex) {
            Logger.getLogger(UDPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Resposta: " + new String(resposta.getData()));
    }

    @Override
    public void run() {
        escutar();
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    

    /**
     * @return the porta
     */
    public int getPorta() {
        return servidorPorta;
    }

   
}
