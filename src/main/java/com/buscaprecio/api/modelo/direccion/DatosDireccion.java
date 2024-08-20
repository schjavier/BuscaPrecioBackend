package com.buscaprecio.api.modelo.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String barrio,
        @NotBlank
        String provincia
) {
}
