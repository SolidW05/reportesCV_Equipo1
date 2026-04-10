package com.proyecto.autoridades_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    @Column(name = "idMunicipio")
    private Integer idMunicipio;

    @Column(name = "municipio")
    private String municipio;
}