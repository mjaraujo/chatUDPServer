/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model.pessoa;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
/**
 *
 * @author marcio
 */
@Entity
@DiscriminatorValue(value = "MODERADOR")

public class Moderador extends Pessoa {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", insertable = false, updatable = false, nullable = false, length = 20)
    private TipoPessoaEnum tipo;

    public Moderador() {
    }
/**
     * @return the tipo
     */
    @Override
    public TipoPessoaEnum getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    @Override
    public void setTipo(TipoPessoaEnum tipo) {
        this.tipo = tipo;
    }
}

