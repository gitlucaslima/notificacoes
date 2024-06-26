package br.jus.tjac.notificacaoservice.exceptions;

import java.util.UUID;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(UUID id) {
        super(String.format("Notification with id %s not found", id));
    }
}
