/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.dao.Pessoa;

import com.mjasistemas.chatclientudp.dao.GenericDAO;
import com.mjasistemas.chatclientudp.model.Banidos;
import com.mjasistemas.chatclientudp.model.Sessao;
import java.util.List;

/**
 *
 * @author marcio
 */
public class BanidoDao extends GenericDAO<Banidos> {

    public List<Banidos> getByPessoaSala(int idPessoa, int idSala) {
        this.addParams("pessoa", String.valueOf(idPessoa));
        this.addParams("sala", String.valueOf(idSala));
        return this.newQueryBuilder(Banidos.class);
    }

  
}
