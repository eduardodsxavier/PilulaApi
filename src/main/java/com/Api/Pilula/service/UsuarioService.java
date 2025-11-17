package com.Api.Pilula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.dtos.UsuarioInfoDto;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.security.SecurityConfig;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JwtService jwtService;

    public UsuarioInfoDto userInformation(HttpServletRequest request) {
        String cpf = jwtService.getSubjectFromRequest(request);
        Usuario usuario = repository.findByCpf(cpf).get();
        return new UsuarioInfoDto(usuario.cpf(), usuario.nome(), usuario.email());
    }

    public UsuarioInfoDto update(HttpServletRequest request, Usuario usuarioInfo) {
        String cpf = jwtService.getSubjectFromRequest(request);
        Usuario usuario = repository.findById(cpf.trim()).get();

        if (usuarioInfo.senha() != null && !new Usuario().validarSenha(usuarioInfo.senha())) {
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

    public void delete(HttpServletRequest request) {
        repository.deleteById(jwtService.getSubjectFromRequest(request));
    }
}
