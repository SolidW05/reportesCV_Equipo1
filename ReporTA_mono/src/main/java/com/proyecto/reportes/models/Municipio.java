package com.proyecto.reportes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "municipios")
public class Municipio {
    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMunicipio;

    @Column(name = "municipio")
    private String municipio;
}
