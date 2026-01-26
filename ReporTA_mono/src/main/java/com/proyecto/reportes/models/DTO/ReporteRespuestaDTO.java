package com.proyecto.reportes.models.DTO;

import com.proyecto.reportes.models.Reporte;
import jakarta.persistence.Lob;

import java.time.LocalDate;

public class ReporteRespuestaDTO {
    public ReporteRespuestaDTO(Integer idReporte, String descripcion, Reporte.EstadoReporte estado, LocalDate fechaReporte,
                                byte[] foto,
                               String calle, String numero, String colonia, String codigoPostal, String municipio,
                               String nombre, String autoridad, String servicio, String telefono, String observaciones,
                                Integer idMunicipio, Integer idAutoridad, Double latitud, Double longitud ) {
        this.idReporte = idReporte;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaReporte = fechaReporte;
        this.foto = foto;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
        this.nombre = nombre;
        this.autoridad = autoridad;
        this.servicio = servicio;
        this.telefono = telefono;
        this.observaciones = observaciones;
        this.idMunicipio = idMunicipio;
        this.idAutoridad = idAutoridad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public ReporteRespuestaDTO() {

    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Reporte.EstadoReporte getEstado() {
        return estado;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public String getServicio() {
        return servicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(Reporte.EstadoReporte estado) {
        this.estado = estado;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdAutoridad() {
        return idAutoridad;
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

    public void setIdAutoridad(Integer idAutoridad) {
        this.idAutoridad = idAutoridad;
    }
    private Integer idReporte;
    private String descripcion;
    private Reporte.EstadoReporte estado;
    private LocalDate fechaReporte;

    private byte[] foto;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String municipio;
    private String nombre;
    private String autoridad;
    private String servicio;
    private String telefono;
    private String observaciones;
    private Integer idMunicipio;
    private Integer idAutoridad;
    private Double latitud;
    private Double longitud;

}
