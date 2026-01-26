package com.proyecto.reportes.models.DTO;

import java.time.LocalDate;
import java.util.Locale;

public class ReporteCrearDTO {

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdAutoridad() {
        return idAutoridad;
    }

    public void setIdAutoridad(Integer idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ReporteCrearDTO(Integer idUsuario, Integer idAutoridad, Integer municipio, String colonia, String calle, String numero, String codigoPostal, LocalDate fecha, String descripcion, Double latitud, Double longitud) {
        this.idUsuario = idUsuario;
        this.idAutoridad = idAutoridad;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // claves foraneas
    private Integer idUsuario;
    private Integer idAutoridad;

    // para la creacion de la ubicacion
    private Integer municipio;
    private String colonia;
    private String calle;
    private String numero;
    private String codigoPostal;
    private Double latitud;
    private Double longitud;


    //Propios del reporte
    private LocalDate fecha;
    private String descripcion;



}
