package com.proyecto.autoridades_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutoridadRequest {

    @NotNull(message = "El email del usuario es obligatorio")
    private String email;

    @Size(min = 10, max = 10)
    private String telefono;

    private Integer municipio;
}