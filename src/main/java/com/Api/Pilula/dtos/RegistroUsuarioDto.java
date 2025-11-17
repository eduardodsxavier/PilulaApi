package com.Api.Pilula.dtos;

public record RegistroUsuarioDto(
    String cpf,
    String nome,
    String email,
    String senha
) {}
