package br.jus.tjac.notificacaoservice.controllers;

import br.jus.tjac.notificacaoservice.domain.notificacao.DTOs.NotificacaoRequestDTO;
import br.jus.tjac.notificacaoservice.domain.notificacao.Notificacao;
import br.jus.tjac.notificacaoservice.services.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notificacao")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping
    @MessageMapping("/notifications")
    public ResponseEntity<Notificacao> saveNotification(@RequestBody @Valid NotificacaoRequestDTO body) {
        Notificacao savedNotification = notificacaoService.createNotification(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotification);
    }

}
