package com.Api.Pilula.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistroUsuarioDto(
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    String cpf,
    String nome,
    @NotBlank String email,
    String senha
) {}
