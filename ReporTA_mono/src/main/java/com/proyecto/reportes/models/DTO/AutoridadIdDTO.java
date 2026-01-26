package com.proyecto.reportes.models.DTO;

public class   AutoridadIdDTO {

    public AutoridadIdDTO(Integer id, String autoridad) {
        this.id = id;
        this.autoridad = autoridad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    private Integer id;
    private String autoridad;
}
