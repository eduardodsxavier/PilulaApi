package com.Api.Pilula.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table 
@Entity
public class Usuario {

    @Id
    private String cpf;

    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Usuario() {}
 
    public Usuario(String cpf, String name, String email, String password) {
        this.cpf = cpf;
        this.name = name; 
        this.email = email;
        this.password = password;
    }

    public String cpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String name() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
