package com.Api.Pilula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.dtos.UsuarioInfoDto;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.security.SecurityConfig;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityConfig securityConfig;

    public List<UsuarioInfoDto> getAll() {
        List<UsuarioInfoDto> usuarios = new ArrayList<>(); 
        repository.findAll().stream().forEach(usuario -> usuarios.add(new UsuarioInfoDto(usuario.cpf(), usuario.cpf(), usuario.email()))); 
        return usuarios;
    }

    public UsuarioInfoDto getByCpf(String cpf) {
        Usuario usuario = repository.findById(cpf).get();
        return new UsuarioInfoDto(usuario.cpf(), usuario.nome(), usuario.email());
    }

    public UsuarioInfoDto update(String cpf, Usuario usuarioInfo) {
        Usuario usuario = repository.findById(cpf).get();

        if(usuarioInfo.senha() != null && !new Usuario().validarSenha(usuarioInfo.senha())) {
            throw new RuntimeException();
        }

        if (usuarioInfo.nome() != null) {
            usuario.setNome(usuarioInfo.nome().trim());
        }
        if (usuarioInfo.email() != null) {
            usuario.setEmail(usuarioInfo.email().trim());
        }
        if (usuarioInfo.senha() != null) {
            usuario.setSenha(securityConfig.passwordEncoder().encode(usuarioInfo.senha().trim()));
        }

        repository.save(usuario);

        return new UsuarioInfoDto(usuario.cpf(), usuario.nome(), usuario.email());
    }

    public void delete(String cpf) {
        repository.deleteById(cpf);
    }
}
