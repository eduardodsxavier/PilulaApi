package com.Api.Pilula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Api.Pilula.dtos.UsuarioInfoDto;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioInfoDto> getAllUsuarios() {
        return service.getAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioInfoDto> getUsuarioByCpf(@PathVariable String cpf) {
        UsuarioInfoDto usuario = service.getByCpf(cpf);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioInfoDto> updateUsuario(@PathVariable String cpf, @RequestBody Usuario usuarioAtualizado) {
        UsuarioInfoDto usuario = service.update(cpf, usuarioAtualizado);
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
