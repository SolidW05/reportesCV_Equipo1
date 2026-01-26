package com.proyecto.reportes.models.DTO;

public class UsuarioRegistroDTO {
    private String nombre;
    private String email;
    private String password;
   // private String curp;


    public UsuarioRegistroDTO(){}
        public String getNombre(){
            return nombre;

    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    /*public String getCurp(){
        return curp;
    }public void setCurp(String curp){
        this.curp = curp;
    }*/
}
