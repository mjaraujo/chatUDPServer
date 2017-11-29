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
public enum StatusLoginPessoaEnum {

    OK("ok"),
    NAO_EXISTE("Usuário não cadastrado"),
    SENHA_INVALIDA("Senha inválida");
    
    private final String descricao;

    private StatusLoginPessoaEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
