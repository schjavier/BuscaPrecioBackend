package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.Direccion;

import java.time.LocalDateTime;

public record DatosListadoComercios(
        Long id,
        String nombre,
        Direccion direccion
) {

    public DatosListadoComercios(Comercio comercio){
        this(comercio.getId(), comercio.getNombre(), comercio.getDireccion());
    }

}
