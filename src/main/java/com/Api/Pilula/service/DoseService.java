package com.Api.Pilula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.DoseRepository;
import com.Api.Pilula.model.Dose;

@Service
public class DoseService {

    @Autowired 
    private DoseRepository repository;
    
    public Dose save(Dose dose) {
        return repository.save(dose);
    }

    public List<Dose> getAll() {
        return repository.findAll();
    }
     
    public Optional<Dose> getById(Long id) {
        return repository.findById(id);
    }

    public List<Dose> getByMedicamentoId(Long medicamentoId) {
        return repository.findByMedicamentoId(medicamentoId);
    }

    public Dose update(Long id, Dose newInfo) {
        Dose dose = repository.findById(id).orElseThrow();

        dose.setMedicamento(newInfo.medicamento());
        dose.setHoraPrevista(newInfo.horaPrevista());
        dose.setStatus(newInfo.status());

        return repository.save(dose);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
