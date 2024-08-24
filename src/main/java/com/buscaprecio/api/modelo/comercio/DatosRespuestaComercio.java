package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.DatosDireccion;
import com.buscaprecio.api.modelo.direccion.Direccion;
import com.buscaprecio.api.modelo.user.User;

import java.time.LocalDateTime;

public record DatosRespuestaComercio(
        Long id,
        String nombre,
        DatosDireccion direccion,
        LocalDateTime fecha,
        User encargado) {
}
