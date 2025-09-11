package com.Api.Pilula.model;

import com.Api.Pilula.enums.Status;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table 
@Entity
public class Dose {

    @Id
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;
    
    @Column(unique = true, nullable = false)
    private Time horaPrevista;

    @Column(nullable = false)
    private Status status;

    public Dose() {}
 
    public Dose(Long id, Time horaPrevista, Status status) {
        this.id = id;
        this.horaPrevista = horaPrevista; 
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Time getHoraPrevista() {
        return horaPrevista;
    }

    public void setHoraPrevista(Time horaPrevista) {
        this.horaPrevista = horaPrevista;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
