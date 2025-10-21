package com.Api.Pilula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getAll() {
        return repository.findAll();
    }

    public Usuario getByCpf(String cpf) {
        Optional<Usuario> usuario = repository.findById(cpf);
        return usuario.orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario update(String cpf, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = repository.findById(cpf);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setSenha(usuarioAtualizado.senha());
            usuario.setEmail(usuarioAtualizado.email());
            usuario.setSenha(usuarioAtualizado.senha());
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
