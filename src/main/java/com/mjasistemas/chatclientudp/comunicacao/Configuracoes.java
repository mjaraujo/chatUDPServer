/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.comunicacao;

/**
 *
 * @author marcio
 */
public class Configuracoes {

   
    private static String IP;
    private static int  Porta;
    
   
    
    /**
     * @return the IP
     */
    public static String getIP() {
        return IP;
    }

    /**
     * @param IP the IP to set
     */
    public static void setIP(String IP) {
        Configuracoes.IP = IP;
    }

    /**
     * @return the Porta
     */
    public static int getPorta() {
        return Porta;
    }

    /**
     * @param Porta the Porta to set
     */
    public static void setPorta(int Porta) {
        Configuracoes.Porta = Porta;
    }
}
