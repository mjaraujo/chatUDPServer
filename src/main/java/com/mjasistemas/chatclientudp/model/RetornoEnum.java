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
public enum RetornoEnum {

    ENTRADA_OK("Entrada permitida"),
    ENTRADA_BANIDO("Usuário banido da sala"),
    ENTRADA_NAO_CADASTRADO("Usuário não cadastrado"),
    ERRO_SIZE("Tamanho da mensagem incorreto");

    private final String descricao;

    private RetornoEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
