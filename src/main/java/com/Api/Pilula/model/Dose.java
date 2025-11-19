package com.Api.Pilula.model;

import com.Api.Pilula.enums.Status;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Table(name = "dose") 
@Entity
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Medicamento medicamento;
    
    @Column(nullable = false)
    private Time horaPrevista;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Dose() {}
 
    public Dose(Long id, Medicamento medicamento, Time horaPrevista, Status status) {
        this.id = id;
        this.medicamento = medicamento;
        this.horaPrevista = horaPrevista; 
        this.status = status;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicamento medicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Time horaPrevista() {
        return horaPrevista;
    }

    public void setHoraPrevista(Time horaPrevista) {
        this.horaPrevista = horaPrevista;
    }

    public Status status() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
