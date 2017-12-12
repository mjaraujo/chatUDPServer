/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.controller;

import com.mjasistemas.chatclientudp.FormatFacade;
import com.mjasistemas.chatclientudp.dao.Pessoa.SalaDao;
import com.mjasistemas.chatclientudp.dao.mensagem.MensagemDao;
import com.mjasistemas.chatclientudp.model.Mensagem;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marcio
 */
public class MensagemController {

    public List<Mensagem> solicitarNovasMensagens(int idSala, Timestamp timestamp) {
        return new MensagemDao().getUltimasMensagens(new SalaDao().getById(idSala), timestamp);
    }
}
