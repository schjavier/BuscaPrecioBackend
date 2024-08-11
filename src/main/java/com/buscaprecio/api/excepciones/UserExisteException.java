package com.buscaprecio.api.excepciones;

public class UserExisteException extends RuntimeException{

    public UserExisteException(String msg){
        super(msg);
    }

}
