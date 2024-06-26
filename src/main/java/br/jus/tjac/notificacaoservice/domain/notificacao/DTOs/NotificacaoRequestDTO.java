package br.jus.tjac.notificacaoservice.domain.notificacao.DTOs;

import br.jus.tjac.notificacaoservice.domain.notificacao.Notificacao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NotificacaoRequestDTO {

    @NotEmpty(message = "Assunto é obrigatório")
    private String assunto;
    @NotNull(message = "Destinatário é obrigatório")
    private UUID clientId;
    @NotEmpty(message = "Remetente é obrigatório")
    private String destinatarioUsername;
    @NotEmpty(message = "Remetente é obrigatório")
    private String remetenteUsername;
    @NotEmpty(message = "Descricão é obrigatório")
    private String descricao;

    public static NotificacaoRequestDTO modelToDto(Notificacao notificacao) {
        NotificacaoRequestDTO dto = new NotificacaoRequestDTO();
        dto.setAssunto(notificacao.getAssunto());
        dto.setClientId(notificacao.getClient());
        dto.setDestinatarioUsername(notificacao.getDestinatarioUsername());
        dto.setRemetenteUsername(notificacao.getRemetenteUsername());
        dto.setDescricao(notificacao.getDescricao());
        return dto;
    }

    public Notificacao dtoToModel() {
        Notificacao notificacao = new Notificacao();
        notificacao.setAssunto(this.assunto);
        notificacao.setClient(this.clientId);
        notificacao.setDestinatarioUsername(this.destinatarioUsername);
        notificacao.setRemetenteUsername(this.remetenteUsername);
        notificacao.setDescricao(this.descricao);
        return notificacao;
    }
}
