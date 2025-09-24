package com.Api.Pilula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.MedicamentoRepository;
import com.Api.Pilula.model.Medicamento;

@Service
public class MedicamentoService {

    @Autowired 
    private MedicamentoRepository repository;
    
    public Medicamento save(Medicamento medicamento) {
        return repository.save(medicamento);
    }

    public List<Medicamento> getAll() {
        return repository.findAll();
    }
     
    public Optional<Medicamento> getById(Long id) {
        return repository.findById(id);
    }

    public List<Medicamento> getByUsuarioCpf(String usuarioCpf) {
        return repository.findByUsuarioCpf(usuarioCpf);
    }

    public Medicamento update(Long id, Medicamento newInfo) {
        Medicamento medicamento = repository.findById(id).orElseThrow();

        medicamento.setNome(newInfo.nome());
        medicamento.setDosagem(newInfo.dosagem());
        medicamento.setAdministracao(newInfo.administracao());
        medicamento.setFrequencia(newInfo.frequencia());
        medicamento.setInicio(newInfo.inicio());
        medicamento.setTermino(newInfo.termino());
        medicamento.setContinuo(newInfo.continuo());
        medicamento.setObservacoes(newInfo.observacoes());

        return repository.save(medicamento);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
