package com.proyecto.autoridades_service.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder builder) {
        this.webClient = builder
            .baseUrl("http://USER-SERVICE")
            .build();
    }

    public Long cambiarTipoUsuario(String email) {
        System.out.println("USING PATCH METHOD");
        return webClient.patch()
            .uri("/api/users/cambiar-tipo/{email}", email)
            .retrieve()
            .bodyToMono(Long.class)  
            .block();                 
    }

}
