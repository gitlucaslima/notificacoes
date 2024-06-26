package br.jus.tjac.notificacaoservice.domain.keycloack.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserResponseDTO {

    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String matricula;
    private List<Role> roles;

    @Override
    public String toString() {
        return "UpdateUserResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matricula='" + matricula + '\'' +
                ", roles=" + roles+
                '}';
    }
}
