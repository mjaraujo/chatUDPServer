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
public enum StatusSolicitacaoEnum {

    OCIOSO("Ocioso"),
    PROCESSANDO("Processando"),
    RESPONDIDA("Respondida"),
    TIME_OUT("Time out");

    private final String descricao;

    private StatusSolicitacaoEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
