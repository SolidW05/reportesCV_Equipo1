package com.reporta.report_service.config;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class UbicacionClient {

    private final WebClient webClient;

    public UbicacionClient(WebClient.Builder builder) {
        this.webClient = builder
            .baseUrl("http://UBICACION")
            .build();
    }

    public List<Long> obtenerIdsPorMunicipio(String municipio) {
        return webClient.get()
            .uri("/api/ubicacion/{municipio}", municipio)
            .retrieve()
            .bodyToFlux(Long.class)  
            .collectList()            
            .block();                 
    }
}
