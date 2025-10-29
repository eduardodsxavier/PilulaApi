package com.Api.Pilula.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.MedicamentoRepository;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.dtos.MedicamentoInfoDto;
import com.Api.Pilula.model.Medicamento;
import com.Api.Pilula.model.Usuario;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public MedicamentoInfoDto save(MedicamentoInfoDto medicamentoInfo) {
        Usuario usuario = usuarioRepository.findByCpf(medicamentoInfo.cpfUsuario()).get(); 

        Medicamento medicamento = new Medicamento();
        medicamento.setUsuario(usuario);
        medicamento.setNome(medicamentoInfo.nome().trim());
        medicamento.setDosagem(medicamentoInfo.dosagem().trim());
        medicamento.setAdministracao(medicamentoInfo.administracao());
        medicamento.setFrequencia(medicamentoInfo.frequencia().trim());
        medicamento.setInicio(medicamentoInfo.inicio());
        medicamento.setTermino(medicamentoInfo.termino());
        medicamento.setContinuo(medicamentoInfo.continuo());
        medicamento.setObservacoes(medicamentoInfo.observacoes().trim());

        repository.save(medicamento);

        return medicamentoInfo;
    }

    public List<MedicamentoInfoDto> getAll() {
        List<MedicamentoInfoDto> medicamentos = new ArrayList<>(); 
        repository.findAll().stream().forEach(medicamento -> medicamentos.add(new MedicamentoInfoDto(
                        medicamento.id(),
                        medicamento.usuario().cpf(), 
                        medicamento.nome(), 
                        medicamento.dosagem(), 
                        medicamento.administracao(), 
                        medicamento.frequencia(), 
                        medicamento.inicio(), 
                        medicamento.termino(), 
                        medicamento.continuo(), 
                        medicamento.observacoes()))); 
        return medicamentos;
    }

    public MedicamentoInfoDto getById(Long id) {
        Medicamento medicamento = repository.findById(id).get();

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
                        medicamento.observacoes()); 
    }

    public List<MedicamentoInfoDto> getByUsuarioCpf(String usuarioCpf) {
        List<MedicamentoInfoDto> medicamentos = new ArrayList<>(); 
        repository.findByUsuarioCpf(usuarioCpf.trim()).stream().forEach(medicamento -> medicamentos.add(new MedicamentoInfoDto(
                        medicamento.id(),
                        usuarioCpf, 
                        medicamento.nome(), 
                        medicamento.dosagem(), 
                        medicamento.administracao(), 
                        medicamento.frequencia(), 
                        medicamento.inicio(), 
                        medicamento.termino(), 
                        medicamento.continuo(), 
                        medicamento.observacoes()))); 
        return medicamentos;
    }

    public MedicamentoInfoDto update(Long id, MedicamentoInfoDto medicamentoInfo) {
        Medicamento medicamento = repository.findById(id).orElseThrow();

        medicamento.setNome(medicamentoInfo.nome().trim());
        medicamento.setDosagem(medicamentoInfo.dosagem().trim());
        medicamento.setAdministracao(medicamentoInfo.administracao());
        medicamento.setFrequencia(medicamentoInfo.frequencia().trim());
        medicamento.setInicio(medicamentoInfo.inicio());
        medicamento.setTermino(medicamentoInfo.termino());
        medicamento.setContinuo(medicamentoInfo.continuo());
        medicamento.setObservacoes(medicamentoInfo.observacoes().trim());

        repository.save(medicamento);

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
                        medicamento.observacoes()); 
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
