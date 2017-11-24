/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.comunicacao;

import com.mjasistemas.chatclientudp.model.RetornoEntradaEnum;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.pessoa.Usuario;

/**
 *
 * @author marcio
 */
public class Solicitacoes {
    UDPCliente udpc = new UDPCliente(Configuracoes.getIP(), Configuracoes.getPorta());
        
    public RetornoEntradaEnum solicitarEntrada(String usuario, Integer sala){
        boolean ret = false;
        String msg = "1";
        msg += String.format("%05d", sala); //numeros estranhos complete do lado esquedo com mais coisas
        msg += String.format("%12s", usuario);
        
        udpc.enviar(msg);
        udpc.run();
        return RetornoEntradaEnum.NAO_CADASTRADO;
    }
}

