/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.dao.Pessoa;

import com.mjasistemas.chatclientudp.dao.GenericDAO;
import com.mjasistemas.chatclientudp.model.Sessao;
import java.util.List;

/**
 *
 * @author marcio
 */
public class SessaoDao extends GenericDAO<Sessao> {

    public Sessao getById(int id) {
        return this.listOne("id", id, Sessao.class);
    }

    public List<Sessao> getAll() {
        return this.listAll(Sessao.class);
    }

}
