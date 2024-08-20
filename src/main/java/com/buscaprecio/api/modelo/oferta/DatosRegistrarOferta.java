package com.buscaprecio.api.modelo.oferta;

import com.buscaprecio.api.modelo.comercio.Comercio;

public record DatosRegistrarOferta(String descripcion, Float precio, Comercio comercio) {
}
