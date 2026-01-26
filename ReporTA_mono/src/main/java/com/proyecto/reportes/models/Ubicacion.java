package com.proyecto.reportes.models;

import jakarta.persistence.*;
@Entity
@Table(name = "Ubicacion")
public class Ubicacion {
    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    public void setLatitud(Double latitud){
        this.latitud = latitud;
    }
    public double getLongitud(){
        return longitud;
    }

    public void setLongitud(Double longitud){
        this.longitud = longitud;}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUbicacion;

    @Column(nullable = false, length = 80)
    private String calle;

    @Column(name = "Num", nullable = false, length = 10)
    private String numero;

    @Column(nullable = false, length = 45)
    private String colonia;

    @Column(nullable = false, length = 8)
    private String codigoPostal;
        @Column(nullable = false)
        private Double latitud;

        @Column(nullable = false)
        private Double longitud;

        public Double getLatitud() {
            return latitud;
        }

    @ManyToOne
    @JoinColumn(name = "municipio")
    private Municipio municipio;

    public Ubicacion(String calle, String numero,
                     String colonia, String codigoPostal,
                     Municipio municipio) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
    }

    public Ubicacion() {
    }
}
