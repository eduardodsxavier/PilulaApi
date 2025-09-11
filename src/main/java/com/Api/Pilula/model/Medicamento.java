package com.Api.Pilula.model;

import com.Api.Pilula.enums.Administracao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table 
@Entity
public class Medicamento {

    @Id
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String dosagem;

    @Column(nullable = false)
    private Administracao administracao;
    
    @Column(nullable = false)
    private String frequencia;

    @Column(nullable = false)
    private Date inicio;

    private Date termino;

    private boolean continuo;

    private String observacoes;

    public Medicamento() {}

    public Medicamento(Long id, Usuario usuario, String nome, String dosagem, Administracao administracao, String frequencia, Date inicio, Date termino, boolean continuo, String observacoes) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.dosagem = dosagem;
        this.administracao = administracao;
        this.frequencia = frequencia;
        this.inicio = inicio;
        this.termino = termino;
        this.continuo = continuo;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public Administracao getAdministracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public boolean getContinuo() {
        return continuo;
    }

    public void setContinuo(boolean continuo) {
        this.continuo = continuo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
