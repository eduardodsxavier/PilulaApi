package com.Api.Pilula.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.MedicamentoRepository;
import com.Api.Pilula.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

import com.Api.Pilula.dtos.MedicamentoInfoDto;
import com.Api.Pilula.model.Medicamento;
import com.Api.Pilula.model.Usuario;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public MedicamentoInfoDto save(MedicamentoInfoDto medicamentoInfo, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findByCpf(jwtService.getSubjectFromRequest(request)).get(); 

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

    public MedicamentoInfoDto getById(Long id, HttpServletRequest request) {
        Medicamento medicamento = repository.findById(id).get();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        if (!medicamento.usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException();
        }

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

    public List<MedicamentoInfoDto> getByUsuario(HttpServletRequest request) {
        List<MedicamentoInfoDto> medicamentos = new ArrayList<>(); 
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        repository.findByUsuarioCpf(usuarioCpf).stream().forEach(medicamento -> medicamentos.add(new MedicamentoInfoDto(
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

    public MedicamentoInfoDto update(Long id, MedicamentoInfoDto medicamentoInfo, HttpServletRequest request) {
        Medicamento medicamento = repository.findById(id).orElseThrow();
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        if (!medicamento.usuario().cpf().equals(usuarioCpf)) {
            throw new RuntimeException();
        }

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

    public void delete(Long id, HttpServletRequest request) {
        repository.deleteByIdAndUsuarioCpf(id, jwtService.getSubjectFromRequest(request));
    }
}
