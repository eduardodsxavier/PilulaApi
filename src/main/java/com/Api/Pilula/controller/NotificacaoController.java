package com.Api.Pilula.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.service.JwtService;
import com.Api.Pilula.service.NotificacaoService;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;
    private final JwtService jwtService;

    public NotificacaoController(NotificacaoService notificacaoService, JwtService jwtService) {
        this.notificacaoService = notificacaoService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<DoseInfoDto>> listarNotificacoes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            HttpServletRequest request) {

        String cpf = jwtService.getSubjectFromRequest(request);

        List<DoseInfoDto> notificacoes = notificacaoService.verificarDosesNoPeriodo(inicio, fim, cpf);

        return ResponseEntity.ok(notificacoes);
    }
}

