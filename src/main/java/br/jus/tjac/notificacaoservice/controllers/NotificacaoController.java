package br.jus.tjac.notificacaoservice.controllers;

import br.jus.tjac.notificacaoservice.domain.notificacao.DTOs.NotificacaoRequestDTO;
import br.jus.tjac.notificacaoservice.domain.notificacao.Notificacao;
import br.jus.tjac.notificacaoservice.services.NotificacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notificacao")
@Tag(name = "Notificacao",
        description = "Contem os endpoints que permite gerenciar as notificacoes")
public class NotificacaoController {
    private final NotificacaoService avaliacaoService;

    public NotificacaoController(NotificacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    @MessageMapping("/notification") // Mapeia o endpoint para receber mensagens
    @SendTo("/topic/notifications") // Envia a resposta para o tópico "/topic/notifications"
    public ResponseEntity<Notificacao> save(@RequestBody @Valid NotificacaoRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.createNotification(body));
    }

}
