package br.jus.tjac.notificacaoservice.services;

import br.jus.tjac.notificacaoservice.adapters.Keycloack;
import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.ClientDTO;
import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.Role;
import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.UserResponse;
import br.jus.tjac.notificacaoservice.domain.keycloack.KeyCloackApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KeyCloackService implements Keycloack {


    private final KeyCloackApi keyCloackApi;

    public KeyCloackService(KeyCloackApi keyCloackApi) {
        this.keyCloackApi = keyCloackApi;
    }


    @Override
    public List<Role> getRoles() {
        return this.keyCloackApi.getRoles();
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return this.keyCloackApi.getUserByUsename(username);
    }

    @Override
    public List<ClientDTO> getClients() {
        return this.keyCloackApi.getClients();
    }

    @Override
    public ClientDTO getClientByUUID(UUID uuid) {
        return this.keyCloackApi.getClientByUUID(uuid);
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        return this.keyCloackApi.findUserByEmail(email);
    }


}
