/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcio
 */
@Entity
@Table(name = "tb_banidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banidos.findAll", query = "SELECT b FROM Banidos b")
    , @NamedQuery(name = "Banidos.findById", query = "SELECT b FROM Banidos b WHERE b.id = :id")
    , @NamedQuery(name = "Banidos.findByTipo", query = "SELECT b FROM Banidos b WHERE b.tipo = :tipo")
    , @NamedQuery(name = "Banidos.findByPessoa", query = "SELECT b FROM Banidos b WHERE b.pessoa = :pessoa")
    , @NamedQuery(name = "Banidos.findBySala", query = "SELECT b FROM Banidos b WHERE b.sala = :sala")})
public class Banidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer id;
    private String tipo;
    private Integer pessoa;
    private Integer sala;

    public Banidos() {
    }

    public Banidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banidos)) {
            return false;
        }
        Banidos other = (Banidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mjasistemas.chatclientudp.model.Banidos[ id=" + id + " ]";
    }
    
}
