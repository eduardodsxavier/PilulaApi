package com.Api.Pilula.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.DoseRepository;
import com.Api.Pilula.repository.MedicamentoRepository;
import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.model.Dose;
import com.Api.Pilula.model.Medicamento;

@Service
public class DoseService {

    @Autowired 
    private DoseRepository repository;

    @Autowired 
    private MedicamentoRepository medicamentoRepository;
    
    public DoseInfoDto save(DoseInfoDto doseInfo) {
        Medicamento medicamento = medicamentoRepository.findById(doseInfo.medicamentoId()).get();
        Dose dose = new Dose();
        dose.setMedicamento(medicamento);
        dose.setHoraPrevista(doseInfo.horaPrevista());
        dose.setStatus(doseInfo.status());
        repository.save(dose);
        return doseInfo;
    }

    public List<DoseInfoDto> getAll() {
        List<DoseInfoDto> doses = new ArrayList<>();
        repository.findAll().stream().forEach(dose -> doses.add(new DoseInfoDto(dose.id(), dose.medicamento().id(), dose.horaPrevista(), dose.status())));
        return doses;
    }
     
    public DoseInfoDto getById(Long id) {
        Dose dose = repository.findById(id).get();
        return new DoseInfoDto(dose.id(), dose.medicamento().id(), dose.horaPrevista(), dose.status());
    }

    public List<DoseInfoDto> getByMedicamentoId(Long medicamentoId) {
        List<DoseInfoDto> doses = new ArrayList<>();
        repository.findByMedicamentoId(medicamentoId).stream().forEach(dose -> doses.add(new DoseInfoDto(dose.id(), dose.medicamento().id(), dose.horaPrevista(), dose.status())));
        return doses;
    }

    public DoseInfoDto update(Long id, DoseInfoDto newInfo) {
        Dose dose = repository.findById(id).orElseThrow();

        if (newInfo.horaPrevista() != null) {
            dose.setHoraPrevista(newInfo.horaPrevista());
        }

        if (newInfo.status() != null) {
            dose.setStatus(newInfo.status());
        }

        repository.save(dose);
        return newInfo;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
