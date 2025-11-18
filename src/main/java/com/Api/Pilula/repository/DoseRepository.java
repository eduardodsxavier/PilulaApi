package com.Api.Pilula.repository;

import java.util.Optional;
import java.sql.Time;
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

    @Query(value = "SELECT d.* FROM dose d JOIN medicamento m ON d.medicamento_id = m.id WHERE m.usuario_cpf = :cpf AND d.hora_prevista BETWEEN :inicio AND :fim", nativeQuery = true)
    List<Dose> buscarDosesPorCpfEPeriodo(
            @Param("cpf") String cpf,
            @Param("inicio") Time inicio,
            @Param("fim") Time fim
            );
}
