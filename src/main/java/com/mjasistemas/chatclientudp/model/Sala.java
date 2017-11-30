/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcio
 */
@Entity
@Table(name = "tb_sala", catalog = "chatudp", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findById", query = "SELECT s FROM Sala s WHERE s.id = :id")
    , @NamedQuery(name = "Sala.findByStatus", query = "SELECT s FROM Sala s WHERE s.status = :status")
    , @NamedQuery(name = "Sala.findByCapacidade", query = "SELECT s FROM Sala s WHERE s.capacidade = :capacidade")
    , @NamedQuery(name = "Sala.findByNome", query = "SELECT s FROM Sala s WHERE s.nome = :nome")})
public class Sala implements Serializable {

    /**
     * @return the status
     */
    public StatusSalaEnum getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusSalaEnum status) {
        this.status = status;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
     @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusSalaEnum status;
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer capacidade;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
    private Collection<Sessao> sessaoCollection;

    public Sala() {
    }

    public Sala(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Sessao> getSessaoCollection() {
        return sessaoCollection;
    }

    public void setSessaoCollection(Collection<Sessao> sessaoCollection) {
        this.sessaoCollection = sessaoCollection;
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
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
