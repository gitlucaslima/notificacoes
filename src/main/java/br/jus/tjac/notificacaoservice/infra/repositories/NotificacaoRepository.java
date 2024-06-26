package br.jus.tjac.notificacaoservice.infra.repositories;

import br.jus.tjac.notificacaoservice.domain.notificacao.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {
    List<Notificacao> findByDestinatarioUsernameAndClient(String username, UUID clientUUID);
}

