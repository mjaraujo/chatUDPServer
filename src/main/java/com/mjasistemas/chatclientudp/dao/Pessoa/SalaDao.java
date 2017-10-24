/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.dao.Pessoa;

import com.mjasistemas.chatclientudp.FormatFacade;
import com.mjasistemas.chatclientudp.dao.GenericDAO;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.StatusSalaEnum;
import java.util.List;

/**
 *
 * @author marcio
 */
public class SalaDao extends GenericDAO<Sala> {

    public Sala getById(int id) {
        return this.listOne("id", id, Sala.class);
    }

    public List<Sala> getAllAbertas() {
        this.addParams("status", new FormatFacade().corrigirTermo(StatusSalaEnum.ABERTA.name()));
        return this.newQueryBuilder(Sala.class);
    }

}
