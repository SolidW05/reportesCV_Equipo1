package com.example.UserService.Exception;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException() {
        super("El token es inválido o ha expirado");
    }
}