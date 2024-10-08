package com.buscaprecio.api.excepciones;

import com.buscaprecio.api.excepciones.comercio.ComercioExisteException;
import com.buscaprecio.api.excepciones.comercio.ComercioNotFoundException;
import com.buscaprecio.api.excepciones.user.UserExisteException;
import com.buscaprecio.api.excepciones.user.UserNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.http.HttpResponse;

@ControllerAdvice
public class ExceptionHandlerGlobal {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(@NotNull UserNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExisteException.class)
    public ResponseEntity<String> handleUserExisteException (@NotNull UserExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ComercioExisteException.class)
    public ResponseEntity<String> handleComercioExisteException(@NotNull ComercioExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ComercioNotFoundException.class)
    public ResponseEntity<String> handleComercioNotFoundException(@NotNull ComercioNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
