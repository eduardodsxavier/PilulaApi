package com.Api.Pilula.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegistroUsuarioDto(
    String cpf,
    String nome,
    @NotBlank String email,
    String senha
) {}
