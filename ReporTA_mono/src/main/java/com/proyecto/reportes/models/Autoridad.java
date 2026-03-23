package com.proyecto.reportes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Autoridades")
public class Autoridad {

    public Integer getIdAutoridad() {
        return idAutoridad;
    }
    public void setIdAutoridad(Integer idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutoridades")
    private Integer idAutoridad;

    @ManyToOne
    @JoinColumn(name = "idUsuarios", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 120)
    private String autoridad;

    @Column(nullable = false, length = 45)
    private String servicio;

    @Column(nullable = false, length = 10)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "municipio")
    private Municipio municipio;

}
