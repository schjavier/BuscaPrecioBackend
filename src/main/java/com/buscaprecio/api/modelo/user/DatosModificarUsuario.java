package com.buscaprecio.api.modelo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosModificarUsuario(

        @NotNull
        Long id,
        @NotBlank
        String nombre,
        @NotBlank @Email
        String email,
        @NotBlank
        String password,
        String rol

) {
}
