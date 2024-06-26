package br.jus.tjac.notificacaoservice.services;

import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.ClientDTO;
import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.UserResponse;
import br.jus.tjac.notificacaoservice.domain.notificacao.DTOs.NotificacaoRequestDTO;
import br.jus.tjac.notificacaoservice.domain.notificacao.Notificacao;
import br.jus.tjac.notificacaoservice.exceptions.NotificationNotFoundException;
import br.jus.tjac.notificacaoservice.infra.repositories.NotificacaoRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;
    private final EmailService emailService;

    private final SimpMessagingTemplate messagingTemplate;
    private final KeyCloackService keyCloackService;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, EmailService emailService, SimpMessagingTemplate messagingTemplate, KeyCloackService keyCloackService) {
        this.notificacaoRepository = notificacaoRepository;
        this.emailService = emailService;
        this.messagingTemplate = messagingTemplate;
        this.keyCloackService = keyCloackService;
    }

    public Notificacao createNotification(NotificacaoRequestDTO request) {

        ClientDTO client = keyCloackService.getClientByUUID(request.getClientId());
        UserResponse destinatario = keyCloackService.getUserByUsername(request.getDestinatarioUsername());
        UserResponse remetente = keyCloackService.getUserByUsername(request.getRemetenteUsername());

        // Criar notificação
        Notificacao notificacao = request.dtoToModel();
        notificacao.setId(UUID.randomUUID());
        notificacao.setCreatedAt(LocalDateTime.now());
        notificacao.setUpdateAt(LocalDateTime.now());

        // Procurar destinatario da mensagem
        notificacao.setDestinatarioUsername(destinatario.getUsername());
        notificacao.setRemetenteUsername(remetente.getUsername());

        notificacao.setEmailEnviado(true);
        notificacaoRepository.save(notificacao);

        // Mapper de notificação para notificação de email
        Map<String, Object> variables = this.getNotificationVariables(notificacao);

        String templatePath = client.getName() + "/email-template";

        emailService.sendEmail(destinatario.getEmail(), request.getAssunto(), templatePath, variables);

        // Enviar notificação via WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", notificacao);

        return notificacao;
    }

    public Map<String, Object> getNotificationVariables(Notificacao notification) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("assunto", notification.getAssunto());
        variables.put("client", notification.getClient());
        variables.put("destinatario", notification.getDestinatarioUsername());
        variables.put("remetente", notification.getRemetenteUsername());
        variables.put("descricao", notification.getDescricao());
        variables.put("createdAt", notification.getCreatedAt());
        return variables;
    }

    public Notificacao getNotification(UUID id) {
        return notificacaoRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException(id));
    }

    public List<Notificacao> getUserNotifications(String username, UUID clientUUID) {
        return notificacaoRepository.findByDestinatarioUsernameAndClient(username, clientUUID);
    }
}