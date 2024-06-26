package br.jus.tjac.notificacaoservice.domain.keycloack.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class MappeRoles {
    private List<Role> roles;
    private UUID clientUUID;
}
