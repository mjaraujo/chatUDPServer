/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.dao.mensagem;

import com.mjasistemas.chatclientudp.dao.GenericDAO;
import com.mjasistemas.chatclientudp.model.Mensagem;
import java.util.List;

/**
 *
 * @author marcio
 */
public class MensagemDao extends GenericDAO<Mensagem> {

    public List<Mensagem> getUltimasMensagens(int idSala, String timestamp) {
        addParams("sala",String.valueOf(idSala));
        addParams("timestamp", timestamp);
        return newQueryNamedSingleParam("Mensagem.findByLastTimestamp");
    }

}
