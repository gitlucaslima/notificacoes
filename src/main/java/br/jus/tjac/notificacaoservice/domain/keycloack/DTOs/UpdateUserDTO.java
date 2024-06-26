package br.jus.tjac.notificacaoservice.domain.keycloack.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateUserDTO {
    @NotBlank(message = "Username é obrigatório")
    private String username;
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Primeiro nome é obrigatório")
    private String firstName;
    @NotBlank(message = "Segundo nome é obrigatório")
    private String lastName;
    private String matricula;
    @NotNull(message = "Roles é obrigatório")
    private List<Role> roles;

    @Override
    public String toString() {
        return "UpdateUserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matricula='" + matricula + '\'' +
                ", roles=" + roles +
                '}';
    }
}
