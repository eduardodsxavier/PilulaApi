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
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioInfoDto> updateUsuario(@PathVariable String cpf, @RequestBody Usuario usuarioAtualizado) {
        UsuarioInfoDto usuario = service.update(cpf, usuarioAtualizado);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String cpf) {
        service.delete(cpf);
        return ResponseEntity.noContent().build();
    }
}
