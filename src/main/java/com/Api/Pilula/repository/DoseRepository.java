package com.Api.Pilula.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Api.Pilula.model.Dose;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Long> {
    Optional<Dose> findById(Long id);

    List<Dose> findByMedicamentoId(Long medicamentoId);
}
