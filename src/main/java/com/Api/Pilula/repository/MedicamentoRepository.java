package com.Api.Pilula.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.Api.Pilula.model.Medicamento;

import jakarta.transaction.Transactional;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findById(Long id);

    List<Medicamento> findByUsuarioCpf(String usuarioCpf);

    @Modifying
    @Transactional
    void deleteByIdAndUsuarioCpf(long id, String usuarioCpf);
}
