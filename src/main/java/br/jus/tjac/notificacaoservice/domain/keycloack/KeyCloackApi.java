package br.jus.tjac.notificacaoservice.domain.keycloack;

import br.jus.tjac.notificacaoservice.domain.keycloack.DTOs.*;
import br.jus.tjac.notificacaoservice.exceptions.KeyCloakException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;


@Service
public class KeyCloackApi {

    @Value("${keyCloack.urlBase}")
    private String urlBase;
    @Value("${keyCloack.clientUUID}")
    private UUID clientUUID;
    @Value("${keyCloack.urlAuth}")
    private String urlAuth;

    public List<Role> getRoles() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + AccessToken.token);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<List<Role>> response = restTemplate.exchange(urlAuth + "/usuarios/roles?clientUUID=" + this.clientUUID, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Role>>() {
            });

            return response.getBody();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                this.login();
                return getRoles();
            }

            throw new KeyCloakException("Erro ao buscar Roles: " + e.getMessage());
        }

    }


    public UserResponse getUserByUsename(String username) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + AccessToken.token);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<UserResponse> response = restTemplate.exchange(urlAuth + "/usuarios/" + username, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<UserResponse>() {
            });

            return response.getBody();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                this.login();
                return this.getUserByUsename(username);
            }
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) throw new KeyCloakException("Usuário não encontrado"+e.getMessage());

            throw new KeyCloakException("Erro ao buscar usuário: " + e.getMessage());
        }

    }

    public UserResponse findUserByEmail(String email) {

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + AccessToken.token);
            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<List<UserResponse>> response = restTemplate.exchange(urlBase + "/users?email=" + email, HttpMethod.GET, request, new ParameterizedTypeReference<List<UserResponse>>() {
            });

            if (response.getBody().isEmpty()) {
                throw new KeyCloakException("Usuário não encontrado para o email: " + email);
            }

            return response.getBody().get(0);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                this.login();
                this.findUserByEmail(email);
            }
            throw new KeyCloakException("Erro ao buscar usuario: " + e.getMessage());
        }
    }


    private void login() {
        try {
            HttpHeaders headers = new HttpHeaders();
            RestTemplate rt = new RestTemplate();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("client_id", "auth");
            formData.add("username", "notificacoes");
            formData.add("password", "senha123");
            formData.add("grant_type", "password");
            formData.add("totp", "12312");


            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

            HttpEntity<String> response = rt.postForEntity(this.urlBase + "/protocol/openid-connect/token", entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String token = jsonNode.get("access_token").asText();
            AccessToken.token = token;
        } catch (Exception e) {
            throw new KeyCloakException("Erro ao realizar operação: " + e.getMessage());
        }

    }

    public List<ClientDTO> getClients() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<List<ClientDTO>> clientsResponse = restTemplate.exchange(urlAuth + "/clients", HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientDTO>>() {
            });

            return clientsResponse.getBody();

        } catch (HttpClientErrorException e) {
            throw new KeyCloakException("Erro ao buscar clients");
        }
    }

    public ClientDTO getClientByUUID(UUID uuid) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + AccessToken.token);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ClientDTO> response = restTemplate.exchange(urlAuth + "/clients/" + uuid, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<ClientDTO>() {
            });

            return response.getBody();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                this.login();
                return getClientByUUID(uuid);
            }
            throw new KeyCloakException("Erro ao buscar client: " + e.getMessage());
        }
    }


}
