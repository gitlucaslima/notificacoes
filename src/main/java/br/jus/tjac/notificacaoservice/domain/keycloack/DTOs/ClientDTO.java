package br.jus.tjac.notificacaoservice.domain.keycloack.DTOs;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDTO {

    private String name;
    private String rootUrl;
    private String description;
    private AttributesClient attributes;
}
