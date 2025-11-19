package com.Api.Pilula.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.enums.Status;
import com.Api.Pilula.repository.MedicamentoRepository;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.repository.DoseRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import com.Api.Pilula.dtos.MedicamentoInfoDto;
import com.Api.Pilula.model.Medicamento;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.model.Dose;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DoseRepository doseRepository;

    @Autowired
    private JwtService jwtService;

    public MedicamentoInfoDto save(MedicamentoInfoDto medicamentoInfo, HttpServletRequest request) {

        Usuario usuario = usuarioRepository
                .findByCpf(jwtService.getSubjectFromRequest(request))
                .orElseThrow();

        Medicamento medicamento = new Medicamento();
        medicamento.setUsuario(usuario);
        medicamento.setNome(medicamentoInfo.nome().trim());
        medicamento.setDosagem(medicamentoInfo.dosagem().trim());
        medicamento.setAdministracao(medicamentoInfo.administracao());
        medicamento.setFrequencia(medicamentoInfo.frequencia().trim());
        medicamento.setInicio(medicamentoInfo.inicio());
        medicamento.setTermino(medicamentoInfo.termino());
        medicamento.setContinuo(medicamentoInfo.continuo());
        medicamento.setObservacoes(
            medicamentoInfo.observacoes() != null ? medicamentoInfo.observacoes().trim() : null
        );

        repository.save(medicamento);

        if (medicamentoInfo.horasPrevistas() != null) {
            for (Time hora : medicamentoInfo.horasPrevistas()) {
                Dose dose = new Dose();
                dose.setMedicamento(medicamento);
                dose.setHoraPrevista(hora);
                dose.setStatus(Status.prescrito);
                doseRepository.save(dose);
            }
        }

        List<Time> horas = doseRepository.findByMedicamentoId(medicamento.id())
                .stream()
                .map(Dose::horaPrevista)
                .toList();

        return new MedicamentoInfoDto(
                medicamento.id(),
                usuario.cpf(),
                medicamento.nome(),
                medicamento.dosagem(),
                medicamento.administracao(),
                medicamento.frequencia(),
                medicamento.inicio(),
                medicamento.termino(),
                medicamento.continuo(),
                medicamento.observacoes(),
                horas
        );
    }

    public MedicamentoInfoDto getById(Long id, HttpServletRequest request) {
        Medicamento medicamento = repository.findById(id).orElseThrow();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        if (!medicamento.usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException("Acesso negado ao medicamento.");
        }

        List<Time> horas = doseRepository.findByMedicamentoId(id)
                .stream()
                .map(Dose::horaPrevista)
                .toList();

        return new MedicamentoInfoDto(
                medicamento.id(),
                medicamento.usuario().cpf(),
                medicamento.nome(),
                medicamento.dosagem(),
                medicamento.administracao(),
                medicamento.frequencia(),
                medicamento.inicio(),
                medicamento.termino(),
                medicamento.continuo(),
                medicamento.observacoes(),
                horas
        );
    }

    public List<MedicamentoInfoDto> getByUsuario(HttpServletRequest request) {
        List<MedicamentoInfoDto> medicamentos = new ArrayList<>();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        repository.findByUsuarioCpf(usuarioCpf).forEach(medicamento -> {

            List<Time> horas = doseRepository.findByMedicamentoId(medicamento.id())
                    .stream()
                    .map(Dose::horaPrevista)
                    .toList();

            medicamentos.add(new MedicamentoInfoDto(
                    medicamento.id(),
                    usuarioCpf,
                    medicamento.nome(),
                    medicamento.dosagem(),
                    medicamento.administracao(),
                    medicamento.frequencia(),
                    medicamento.inicio(),
                    medicamento.termino(),
                    medicamento.continuo(),
                    medicamento.observacoes(),
                    horas
            ));
        });

        return medicamentos;
    }

    public MedicamentoInfoDto update(Long id, MedicamentoInfoDto medicamentoInfo, HttpServletRequest request) {
        Medicamento medicamento = repository.findById(id).orElseThrow();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        if (!medicamento.usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException("Acesso negado ao medicamento.");
        }

        medicamento.setNome(medicamentoInfo.nome().trim());
        medicamento.setDosagem(medicamentoInfo.dosagem().trim());
        medicamento.setAdministracao(medicamentoInfo.administracao());
        medicamento.setFrequencia(medicamentoInfo.frequencia().trim());
        medicamento.setInicio(medicamentoInfo.inicio());
        medicamento.setTermino(medicamentoInfo.termino());
        medicamento.setContinuo(medicamentoInfo.continuo());
        medicamento.setObservacoes(
            medicamentoInfo.observacoes() != null ? medicamentoInfo.observacoes().trim() : null
        );

        repository.save(medicamento);

        List<Time> horas = doseRepository.findByMedicamentoId(id)
                .stream()
                .map(Dose::horaPrevista)
                .toList();

        return new MedicamentoInfoDto(
                id,
                medicamento.usuario().cpf(),
                medicamento.nome(),
                medicamento.dosagem(),
                medicamento.administracao(),
                medicamento.frequencia(),
                medicamento.inicio(),
                medicamento.termino(),
                medicamento.continuo(),
                medicamento.observacoes(),
                horas
        );
    }

    @Transactional
    public void delete(Long id, HttpServletRequest request) {
        repository.deleteByIdAndUsuarioCpf(id, jwtService.getSubjectFromRequest(request));
    }
}
