package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.Direccion;

import java.time.LocalDateTime;

public record DatosRespuestaComercio(Long id, String nombre, Direccion direccion, LocalDateTime fecha) {
}
