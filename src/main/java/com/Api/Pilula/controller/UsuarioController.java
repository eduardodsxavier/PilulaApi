package com.Api.Pilula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return service.getAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> getUsuarioByCpf(@PathVariable String cpf) {
        Usuario usuario = service.getByCpf(cpf);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = service.save(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String cpf, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = service.update(cpf, usuarioAtualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String cpf) {
        boolean deleted = service.delete(cpf);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
