package br.jus.tjac.notificacaoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")}, info = @Info(title = "NOTIFICAÇÕES", version = "1.0", description = "API para gerenciamento de notificaçoes"))
@EnableJpaRepositories
public class NotificacaoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificacaoServiceApplication.class, args);
    }

}
