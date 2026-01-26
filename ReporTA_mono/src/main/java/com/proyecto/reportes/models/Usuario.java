package com.proyecto.reportes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }*/

    public boolean isActivo(){
        return activo;
    }
    public void setActivo(boolean activo){
        this.activo = activo;
    }
    public String getTokenVerificacion(){
        return tokenVerificacion;
    }
    public void setTokenVerificacion(String tokenVerificacion){
        this.tokenVerificacion = tokenVerificacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarios")
    private Integer idUsuario;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario = TipoUsuario.usuario;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

   /* @Column(nullable = false, unique = true, length = 18)
    private String curp;*/
    @Column (name = "activo")
    private boolean activo = false;

    @Column(name = "token_Verificacion", length = 255)
    private String tokenVerificacion;

    public  enum TipoUsuario{
        usuario, autoridad
    }
}
