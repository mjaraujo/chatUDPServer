package com.mjasistemas.chatclientudp;

import com.mjasistemas.chatclientudp.server.UDPServidor;
import com.mjasistemas.chatclientudp.view.LoginForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcio
 */
public class principal {

    public static void main(String[] args) {
        UDPServidor u = new UDPServidor();       
        new LoginForm().setVisible(true);
        u.run();
        
    }
}
