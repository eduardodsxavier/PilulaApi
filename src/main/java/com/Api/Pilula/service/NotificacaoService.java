package com.Api.Pilula.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.model.Dose;
import com.Api.Pilula.repository.DoseRepository;

@Service
public class NotificacaoService {

    private final DoseRepository doseRepository;

    public NotificacaoService(DoseRepository doseRepository) {
        this.doseRepository = doseRepository;
    }

    public List<DoseInfoDto> verificarDosesNoPeriodo(LocalDateTime inicio, LocalDateTime fim, String cpfUsuario) {

        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Os parâmetros 'inicio' e 'fim' não podem ser nulos.");
        }

        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("O parâmetro 'fim' deve ser maior que 'inicio'.");
        }

        List<Dose> doses = doseRepository.buscarDosesPorCpfEPeriodo(cpfUsuario, inicio, fim);

        return doses.stream()
                .map(dose -> new DoseInfoDto(
                        dose.id(),
                        dose.medicamento().id(),
                        dose.horaPrevista(),
                        dose.status()
                ))
                .toList();
    }
}

