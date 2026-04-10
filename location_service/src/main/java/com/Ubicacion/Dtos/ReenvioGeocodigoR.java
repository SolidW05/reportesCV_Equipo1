package com.Ubicacion.Dtos;

import lombok.Data;

@Data
public class ReenvioGeocodigoR {

    private Double latitud;
    private Double longitud;
    private String direccionCompleta;
    private String municipio;
    private String estado;
}

