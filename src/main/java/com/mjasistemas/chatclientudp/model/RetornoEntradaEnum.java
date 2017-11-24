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
public enum RetornoEntradaEnum {

    OK("Entrada permitida"),
    BANIDO("Usuário banido da sala"),
    NAO_CADASTRADO("Usuário não cadastrado");

    private final String descricao;

    private RetornoEntradaEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
