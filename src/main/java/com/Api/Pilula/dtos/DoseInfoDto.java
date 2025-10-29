package com.Api.Pilula.dtos;

import java.sql.Time;

import com.Api.Pilula.enums.Status;

public record DoseInfoDto(
    Long id,
    Long medicamentoId,
    Time horaPrevista,
    Status status
) {}
