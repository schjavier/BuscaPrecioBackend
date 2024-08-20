package com.buscaprecio.api.modelo.oferta;

import com.buscaprecio.api.modelo.comercio.Comercio;

public record DatosRespuestaOferta(Long id, String descripcion, Float precio, Comercio comercio) {
}
