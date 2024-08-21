package com.buscaprecio.api.excepciones.comercio;

public class ComercioExisteException extends RuntimeException {
    public ComercioExisteException(String msg) {
        super(msg);
    }
}
