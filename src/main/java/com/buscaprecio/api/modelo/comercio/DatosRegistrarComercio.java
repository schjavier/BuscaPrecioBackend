package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.Direccion;
import com.buscaprecio.api.modelo.user.User;


public record DatosRegistrarComercio(
        String nombre,
        Direccion direccion,
        User encargado) {
}
