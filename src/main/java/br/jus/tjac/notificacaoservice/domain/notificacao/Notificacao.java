package br.jus.tjac.notificacaoservice.domain.notificacao;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table(name = "notificacao", schema = "notificacoes")
@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String assunto;
    private UUID client;
    private String destinatarioUsername;
    private String remetenteUsername;
    private String descricao;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean emailEnviado;

    public Notificacao(UUID id, String assunto, UUID client, String destinatarioUsername, String remetenteUsername, String descricao, LocalDateTime createdAt, LocalDateTime updateAt, boolean emailEnviado) {
        this.id = id;
        this.assunto = assunto;
        this.client = client;
        this.destinatarioUsername = destinatarioUsername;
        this.remetenteUsername = remetenteUsername;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.emailEnviado = emailEnviado;
    }

    public Notificacao() {
    }

}
