package com.Api.Pilula.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.DoseRepository;
import com.Api.Pilula.repository.MedicamentoRepository;

import jakarta.servlet.http.HttpServletRequest;

import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.model.Dose;
import com.Api.Pilula.model.Medicamento;

@Service
public class DoseService {

    @Autowired 
    private DoseRepository repository;

    @Autowired 
    private MedicamentoRepository medicamentoRepository;

    @Autowired 
    private JwtService jwtService;
    
    public DoseInfoDto save(DoseInfoDto doseInfo, HttpServletRequest request) {
        Medicamento medicamento = medicamentoRepository.findById(doseInfo.medicamentoId()).get();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);
        if (!medicamento.usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException();
        }

        Dose dose = new Dose();
        dose.setMedicamento(medicamento);
        dose.setHoraPrevista(doseInfo.horaPrevista());
        dose.setStatus(doseInfo.status());
        repository.save(dose);
        return doseInfo;
    }
     
    public DoseInfoDto getById(Long id, HttpServletRequest request) {
        Dose dose = repository.findById(id).get();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);
        if (!dose.medicamento().usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException();
        }

        return new DoseInfoDto(dose.id(), dose.medicamento().id(), dose.horaPrevista(), dose.status());
    }

    public List<DoseInfoDto> getByMedicamentoId(Long medicamentoId, HttpServletRequest request) {
        List<DoseInfoDto> doses = new ArrayList<>();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);
        repository.findByMedicamentoId(medicamentoId).stream().filter(dose -> dose.medicamento().usuario().cpf().equals(usuarioCpf)).forEach(dose -> doses.add(new DoseInfoDto(dose.id(), dose.medicamento().id(), dose.horaPrevista(), dose.status())));
        return doses;
    }

    public DoseInfoDto update(Long id, DoseInfoDto newInfo, HttpServletRequest request) {
        Dose dose = repository.findById(id).orElseThrow();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);
        if (!dose.medicamento().usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException();
        }

        if (newInfo.horaPrevista() != null) {
            dose.setHoraPrevista(newInfo.horaPrevista());
        }

        if (newInfo.status() != null) {
            dose.setStatus(newInfo.status());
        }

        repository.save(dose);
        return newInfo;
    }

    public void delete(Long id, HttpServletRequest request) {
        Dose dose = repository.findById(id).orElseThrow();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);
        if (!dose.medicamento().usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException();
        }

        repository.deleteById(id);
    }
}
