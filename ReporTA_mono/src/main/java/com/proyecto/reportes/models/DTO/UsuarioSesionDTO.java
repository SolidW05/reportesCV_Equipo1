package com.proyecto.reportes.models.DTO;

import com.proyecto.reportes.models.Usuario.TipoUsuario;

public class UsuarioSesionDTO {

    public UsuarioSesionDTO(String nombre, Integer id, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    private String nombre;
    private Integer id;
    private TipoUsuario tipoUsuario;


}
