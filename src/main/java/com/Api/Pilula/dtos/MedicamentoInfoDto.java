package com.Api.Pilula.dtos;

import com.Api.Pilula.enums.Administracao;

import java.util.Date;

public record MedicamentoInfoDto(
    String cpfUsuario,
    String nome,
    String dosagem,
    Administracao administracao,
    String frequencia,
    Date inicio,
    Date termino,
    Boolean continuo,
    String observacoes
) {}
