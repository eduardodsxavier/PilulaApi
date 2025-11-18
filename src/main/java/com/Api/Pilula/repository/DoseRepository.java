package com.Api.Pilula.repository;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Api.Pilula.model.Dose;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Long> {
    Optional<Dose> findById(Long id);

    List<Dose> findByMedicamentoId(Long medicamentoId);

 @Query("""
    SELECT d FROM Dose d 
    WHERE d.medicamento.usuario.cpf = :cpf
    AND d.horaPrevista BETWEEN :inicio AND :fim
""")
    List<Dose> buscarDosesPorCpfEPeriodo(
        @Param("cpf") String cpf,
        @Param("inicio") LocalDateTime inicio,
        @Param("fim") LocalDateTime fim
    );
}
