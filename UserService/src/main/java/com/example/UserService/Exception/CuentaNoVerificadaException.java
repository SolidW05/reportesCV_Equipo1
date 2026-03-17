package com.example.UserService.Exception;

public class CuentaNoVerificadaException extends RuntimeException {
    public CuentaNoVerificadaException() {
        super("La cuenta no ha sido verificada. Revisa tu correo");
    }
}