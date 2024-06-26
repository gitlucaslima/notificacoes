package br.jus.tjac.notificacaoservice.adapters;


import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.*;

import java.util.List;
import java.util.UUID;

public interface Keycloack {

    List<Role> getRoles();

    UserResponse getUserByUsername(String username);

    List<ClientDTO> getClients();

    ClientDTO getClientByUUID(UUID uuid);

    UserResponse findUserByEmail(String email);
}
