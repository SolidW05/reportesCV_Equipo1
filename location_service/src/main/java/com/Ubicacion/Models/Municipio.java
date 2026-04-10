package com.Ubicacion.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    @Column(name = "idMunicipio")
    private Integer idMunicipio;

    @Column(name = "nombreMunicipio")
    private String municipio;
}