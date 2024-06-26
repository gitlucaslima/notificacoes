package br.jus.tjac.notificacaoservice.domain.keycloack.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Username é obrigatório")
    @CPF
    private String username;
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Primeiro nome é obrigatório")
    private String firstName;
    @NotBlank(message = "Segundo nome é obrigatório")
    private String lastName;
    @NotBlank(message = "Matrícula é obrigatório")
    @Size(min = 8, max = 8, message = "A matricula deve ter 8 caracteres, se necessário adiconar 0 a esquerda")
    private String matricula;
    @NotNull
    private List<Role> roles;
}
