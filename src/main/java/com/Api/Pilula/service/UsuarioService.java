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

    public UsuarioInfoDto update(String cpf, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = repository.findById(cpf);

        if(!new Usuario().validarSenha(usuarioAtualizado.senha())) {
            throw new RuntimeException();
        }

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setEmail(usuarioAtualizado.email().trim());
            usuario.setSenha(securityConfig.passwordEncoder().encode(usuarioAtualizado.senha().trim()));
            repository.save(usuario);
            return new UsuarioInfoDto(usuario.cpf(), usuario.nome(), usuario.email());
        }
        return null;
    }

    public boolean delete(String cpf) {
        if (repository.existsById(cpf)) {
            repository.deleteById(cpf);
            return true;
        }
        return false;
    }
}
