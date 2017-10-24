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
public enum StatusSalaEnum {

    ABERTA("Aberta"),
    CRIADA("Criada"),
    LOTADA("Lotada"),
    ENCERRADA("Encerrada");

    private final String descricao;

    private StatusSalaEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
