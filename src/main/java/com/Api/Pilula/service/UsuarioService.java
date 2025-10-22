package com.Api.Pilula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.security.SecurityConfig;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityConfig securityConfig;

    public List<Usuario> getAll() {
        return repository.findAll();
    }

    public Usuario getByCpf(String cpf) {
        Optional<Usuario> usuario = repository.findById(cpf);
        return usuario.orElse(null);
    }

    public Usuario update(String cpf, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = repository.findById(cpf);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setEmail(usuarioAtualizado.email().trim());
            usuario.setSenha(securityConfig.passwordEncoder().encode(usuarioAtualizado.senha().trim()));
            return repository.save(usuario);
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
