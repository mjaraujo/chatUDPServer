/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcio
 */
@Entity
@Table(name = "tb_mensagem", catalog = "chatUDP", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagem.findAll", query = "SELECT m FROM Mensagem m")
    , @NamedQuery(name = "Mensagem.findById", query = "SELECT m FROM Mensagem m WHERE m.id = :id")
    , @NamedQuery(name = "Mensagem.findByDestinatario", query = "SELECT m FROM Mensagem m WHERE m.destinatario = :destinatario")
    , @NamedQuery(name = "Mensagem.findByRemetente", query = "SELECT m FROM Mensagem m WHERE m.remetente = :remetente")
    , @NamedQuery(name = "Mensagem.findByConteudo", query = "SELECT m FROM Mensagem m WHERE m.conteudo = :conteudo")
    , @NamedQuery(name = "Mensagem.findByTimestamp", query = "SELECT m FROM Mensagem m WHERE m.timestamp = :timestamp")})
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int destinatario;
    @Basic(optional = false)
    @Column(nullable = false)
    private int remetente;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String conteudo;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "sessao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Sessao sessao;

    public Mensagem() {
    }

    public Mensagem(Integer id) {
        this.id = id;
    }

    public Mensagem(Integer id, int destinatario, int remetente, String conteudo, Date timestamp) {
        this.id = id;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.conteudo = conteudo;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

    public int getRemetente() {
        return remetente;
    }

    public void setRemetente(int remetente) {
        this.remetente = remetente;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
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
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mjasistemas.chatclientudp.model.Mensagem[ id=" + id + " ]";
    }

}
