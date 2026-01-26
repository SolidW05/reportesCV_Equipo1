package com.proyecto.reportes.models;


import org.springframework.data.annotation.Immutable;

import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "vw_CatalogoAutoridades")
public class VwCatalogoAutoridades {

    @Id
    @Column(name = "idAutoridades")
    private Long id;

    @Column(name = "Autoridad")
    private String Autoridad;

    @Column(name = "Servicio")
    private String Servicio;

    @Column(name = "Telefono")
    private String Telefono;

    @Column(name = "Encargado")
    private String Encargado;
    
    // Getters and Setters
}
