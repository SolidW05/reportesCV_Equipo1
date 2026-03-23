package com.Ubicacion.Dtos;

import lombok.Data;

@Data
public class GeocodigoInversoR {
    private Long id;

    private Double latitud;
    private Double longitud;
    private String direccionCompleta;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String municipio;
    private String estado;
    private String pais;
}
