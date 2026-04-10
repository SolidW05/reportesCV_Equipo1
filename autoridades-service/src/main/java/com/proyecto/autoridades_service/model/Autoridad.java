package com.proyecto.autoridades_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "autoridades")
public class Autoridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutoridades")
    private Integer idAutoridad;

    @Column(name = "idUsuarios", nullable = false)
    private Integer idUsuario;

    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "municipio")
    private Integer municipioId;
}