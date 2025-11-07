package com.Api.Pilula.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Api.Pilula.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findById(Long id);

    List<Medicamento> findByUsuarioCpf(String usuarioCpf);

    void deleteByIdAndUsuarioCpf(long id, String usuarioCpf);
}
