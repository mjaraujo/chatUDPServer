/*
 * UDPCliente.java
 * Created on 1 de Mar�o de 2002, 21:25
 * @author denisls
 */
package com.mjasistemas.chatclientudp.comunicacao;

import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.StatusSolicitacaoEnum;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPCliente implements Runnable {

    private boolean escutando;
    private StatusSolicitacaoEnum statusSolicitacao;
    private RetornoEnum retornoSolicitacao;
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
            statusSolicitacao = StatusSolicitacaoEnum.PROCESSANDO;
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
                //aSoquete.close();
            }
        }
    }

    private void tratarResposta(String resp) {
        String sResp = resp.substring(0, 1);
        String ret;
        switch (sResp) {
            case "1": //resposta solicitação entrar na sala
                 ret = resp.substring(13, 14);
                switch (ret) {
                    case "0":
                        retornoSolicitacao = RetornoEnum.ENTRADA_OK;
                        break;
                    case "1":
                        retornoSolicitacao = RetornoEnum.ENTRADA_NAO_CADASTRADO;
                        break;
                    case "2":
                        retornoSolicitacao = RetornoEnum.ENTRADA_BANIDO;
                        break;
                }
                break;
            case "0":
                ret = resp.substring(1, 2);
                if (ret == "0") {
                    retornoSolicitacao = RetornoEnum.ERRO_SIZE;
                }
                break;

        }
        statusSolicitacao = StatusSolicitacaoEnum.RESPONDIDA;
    }

    private void escutar() {

        byte[] buffer = new byte[255];
        while (true) {
            DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
            try {
                escutando = true;
                aSoquete.receive(resposta);
                String resp = new String(resposta.getData());
                System.out.println("Resposta ouvida pelo client: " + resp);
                tratarResposta(resp);
                if (statusSolicitacao == StatusSolicitacaoEnum.RESPONDIDA) {
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(UDPCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void run() {
        if (!escutando) {
            escutar();
        }
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

    /**
     * @return the statusSolicitacao
     */
    public StatusSolicitacaoEnum getStatusSolicitacao() {
        return statusSolicitacao;
    }

    /**
     * @param statusSolicitacao the statusSolicitacao to set
     */
    public void setStatusSolicitacao(StatusSolicitacaoEnum statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    /**
     * @return the retornoSolicitacao
     */
    public RetornoEnum getRetornoSolicitacao() {
        return retornoSolicitacao;
    }

    /**
     * @param retornoSolicitacao the retornoSolicitacao to set
     */
    public void setRetornoSolicitacao(RetornoEnum retornoSolicitacao) {
        this.retornoSolicitacao = retornoSolicitacao;
    }

}
