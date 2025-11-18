package com.Api.Pilula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.Api.Pilula.dtos.AccessTokenDto;
import com.Api.Pilula.dtos.LoginUsuarioDto;
import com.Api.Pilula.dtos.RegistroUsuarioDto;
import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.security.UserDetailsImpl;
import com.Api.Pilula.security.SecurityConfig;

import org.springframework.security.core.Authentication;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private SecurityConfig securityConfig;

    public AccessTokenDto register(RegistroUsuarioDto usuarioInfo) {
        String senha = usuarioInfo.senha().trim();
        String cpf = usuarioInfo.cpf().trim();

        if (!new Usuario().validarSenha(senha)) {
            throw new IllegalArgumentException("Senha inválida. Ela deve atender aos requisitos mínimos.");
        }


        if (repository.existsById(cpf)) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com esse CPF.");
        }

        Usuario usuario = new Usuario(
                cpf,
                usuarioInfo.nome().trim(),
                usuarioInfo.email().trim(),
                securityConfig.passwordEncoder().encode(senha));

        repository.save(usuario);

        UserDetailsImpl userDetails = new UserDetailsImpl(usuario);
        String accessToken = jwtService.generateToken(userDetails);

        return new AccessTokenDto(accessToken);
    }

    public AccessTokenDto authenticate(LoginUsuarioDto usuarioInfo) {
        UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(
                usuarioInfo.cpf().trim(), usuarioInfo.senha().trim());

        Authentication authentication = manager.authenticate(userAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);

        return new AccessTokenDto(accessToken);
    }
}
