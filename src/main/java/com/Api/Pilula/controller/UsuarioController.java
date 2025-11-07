package com.Api.Pilula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Api.Pilula.dtos.UsuarioInfoDto;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/me")
    public ResponseEntity<UsuarioInfoDto> getUsuarioByCpf(HttpServletRequest request) {
        UsuarioInfoDto usuario = service.userInformation(request);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/me")
    public ResponseEntity<UsuarioInfoDto> updateUsuario(HttpServletRequest request, @RequestBody Usuario usuarioAtualizado) {
        UsuarioInfoDto usuario = service.update(request, usuarioAtualizado);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUsuario(HttpServletRequest request) {
        service.delete(request);
        return ResponseEntity.noContent().build();
    }
}
