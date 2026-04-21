package com.proyecto.autoridades_service.dto;

import lombok.Data;

@Data
public class AutoridadResponse {
    private Integer idAutoridad;
    private Integer idUsuario;
    private String telefono;
    private Integer municipioId;
    private String municipio;
}