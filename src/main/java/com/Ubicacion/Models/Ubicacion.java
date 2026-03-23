package com.Ubicacion.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @Column(name = "direccion_completa")
    private String direccionCompleta;

    private String calle;
    private String numero;
    private String colonia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false)
    private String estado;

    private String pais;

    @Column(name = "fecha_consulta")
    private LocalDateTime fechaConsulta;

    @PrePersist
    public void prePersist() {
        this.fechaConsulta = LocalDateTime.now();
    }
}