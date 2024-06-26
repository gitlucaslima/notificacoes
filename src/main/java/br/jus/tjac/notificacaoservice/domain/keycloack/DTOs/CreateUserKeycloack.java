package br.jus.tjac.notificacaoservice.domain.keycloack.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserKeycloack {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<Role> roles;
    private UUID clientUUID;
}
