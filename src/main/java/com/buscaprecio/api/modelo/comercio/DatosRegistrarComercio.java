package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.Direccion;

import java.time.LocalDateTime;

public record DatosRegistrarComercio(String nombre, Direccion direccion) {
}
