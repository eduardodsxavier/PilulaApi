package com.Api.Pilula.model;

import com.Api.Pilula.enums.Administracao;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "medicamento")
@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String dosagem;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Administracao administracao;

    @Column(nullable = false)
    private String frequencia;

    @Column(nullable = false)
    private Date inicio;

    private Date termino;

    @Column(nullable = false)
    private boolean continuo;

    private String observacoes;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dose> doses = new ArrayList<>();

    public Medicamento() {
    }

    public Medicamento(Long id, Usuario usuario, String nome, String dosagem, Administracao administracao,
            String frequencia, Date inicio, Date termino, boolean continuo, String observacoes) {
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

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario usuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String nome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String dosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public Administracao administracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public String frequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Date inicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date termino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public boolean continuo() {
        return continuo;
    }

    public void setContinuo(boolean continuo) {
        this.continuo = continuo;
    }

    public String observacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
