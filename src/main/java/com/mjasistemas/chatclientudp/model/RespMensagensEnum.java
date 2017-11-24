/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

/**
 *
 * @author marcio
 */
public enum RespMensagensEnum {

    RESPONDER_SOLICITACAO_ENTRADA("Resposta da solicitação de entrada"),
    RESPONDER_SOLICITACAO_MENSAGEM("Resposta do envio de  mensagem"),
    RESPONDER_SOLICITACAO_BAN_USUARIO("Resposta do envio de voto para banir usuário"),
    RESPONDER_SOLICITACAO_DENUNCIA_MENSAGEM_OFENSIVA("Resposta do envio de denúncia de mensagem ofensiva"),
    RESPONDER_SOLICITACAO_DENUNCIA_MENSAGEM_IMPROPRIA("Resposta do envio de denúncia de mensagem imprópria");

    private final String descricao;

    private RespMensagensEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
