package com.buscaprecio.api.excepciones.user;

public class UserExisteException extends RuntimeException{

    public UserExisteException(String msg){
        super(msg);
    }

}
