package com.Api.Pilula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Api.Pilula.dtos.LoginUsuarioDto;
import com.Api.Pilula.dtos.RegistroUsuarioDto;
import com.Api.Pilula.dtos.AccessTokenDto;
import com.Api.Pilula.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AccessTokenDto> register(@RequestBody RegistroUsuarioDto usuario)  {
        AccessTokenDto accessToken = service.register(usuario);

        return new ResponseEntity<>(new AccessTokenDto(accessToken.token()), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> login(@RequestBody LoginUsuarioDto usuario) {
        AccessTokenDto accessToken = service.authenticate(usuario);

        return new ResponseEntity<>(new AccessTokenDto(accessToken.token()), HttpStatus.OK);
    }
}
