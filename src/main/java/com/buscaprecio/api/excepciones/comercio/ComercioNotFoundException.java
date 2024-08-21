package com.buscaprecio.api.excepciones.comercio;

public class ComercioNotFoundException extends RuntimeException {
    public ComercioNotFoundException(String msg) {
        super(msg);
    }
}
