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
public enum SendMensagensEnum {

    ENTRAR_NA_SALA("Entrar na sala de bate papo"),
    ENVIAR_MENSAGEM("Enviar mensagem"),
    VOTAR_BAN_USUARIO("Enviar voto para banir usuário"),
    DENUNCIAR_MENSAGEM_OFENSIVA("Enviar denúncia de mensagem ofensica"),
    DENUNCIAR_MENSAGEM_IMPROPRIA("Enviar denúncia de mensagem imprópria");

    private final String descricao;

    private SendMensagensEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
