package com.Api.Pilula.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.model.Dose;
import com.Api.Pilula.repository.DoseRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class NotificacaoService {

    @Autowired
    private DoseRepository doseRepository;

    @Autowired
    private JwtService jwtService;

    public List<DoseInfoDto> verificarDosesNoPeriodo(Time inicio, Time fim, HttpServletRequest request) {

        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Os parâmetros 'inicio' e 'fim' não podem ser nulos.");
        }

        if (fim.before(inicio)) {
            throw new IllegalArgumentException("O parâmetro 'fim' deve ser maior que 'inicio'.");
        }

        String cpfUsuario = jwtService.getSubjectFromRequest(request);

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

