package com.example.UserService.Exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String email) {
        super("Usuario no encontrado: " + email);
    }
}