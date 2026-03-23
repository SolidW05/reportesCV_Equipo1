package com.Ubicacion.Exeption;

public class GeoExeption extends RuntimeException {

    public GeoExeption(String mensaje) {
        super(mensaje);
    }

    public GeoExeption(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}