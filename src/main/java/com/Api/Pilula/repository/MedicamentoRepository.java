package com.Api.Pilula.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.Pilula.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findById(Long id);

    Optional<Medicamento> findByNome(String nome);
}
