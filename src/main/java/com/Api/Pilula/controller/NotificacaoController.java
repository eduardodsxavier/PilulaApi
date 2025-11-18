package com.Api.Pilula.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Time;
import java.util.List;
import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.service.NotificacaoService;

@RestController
@RequestMapping("/api/v1/notificacao")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<DoseInfoDto>> listarNotificacoes(@RequestParam Time inicio, @RequestParam Time fim, HttpServletRequest request) {

        List<DoseInfoDto> notificacoes = notificacaoService.verificarDosesNoPeriodo(inicio, fim, request);

        return ResponseEntity.ok(notificacoes);
    }
}

