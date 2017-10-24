/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.dao.Pessoa;

import com.mjasistemas.chatclientudp.FormatFacade;
import com.mjasistemas.chatclientudp.dao.GenericDAO;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;

/**
 *
 * @author marcio
 */
public class PessoaDao extends GenericDAO<Pessoa> {

    public Pessoa getById(int id) {
        return this.listOne("id", id, Pessoa.class);
    }

    public Pessoa getByNickName(String nick) {
        nick = new FormatFacade().corrigirTermo(nick);
        return this.listOne("nickName", nick, Pessoa.class);
    }

}
