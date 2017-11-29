/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.controller;

import com.mjasistemas.chatclientudp.dao.Pessoa.BanidoDao;
import com.mjasistemas.chatclientudp.dao.Pessoa.PessoaDao;
import com.mjasistemas.chatclientudp.model.Banidos;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.StatusLoginPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import java.util.List;

/**
 *
 * @author marcio
 */
public class UsuarioController {

    public boolean permitirAcessoSala(String apelido, int idSala) {
        Integer idPessoa = new PessoaDao().getByNickName(apelido).getId();

        List<Banidos> byPessoaSala = new BanidoDao().getByPessoaSala(idPessoa, idSala);

        return byPessoaSala.size() == 0;
    }

    public Pessoa permitirLogin(String apelido, String senha) {
        Pessoa pessoa = new PessoaDao().getByNickName(apelido);
        if (pessoa == null) {
            return pessoa;
        } else if (!pessoa.getSenha().equals(senha)) {
            pessoa.setId(-1);
        }

        return pessoa;

    }

}