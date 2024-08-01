package com.buscaprecio.api.modelo.user;

public record DatosListadoUsuarios(Long id, String nombre, String email, String rol) {

    public DatosListadoUsuarios(User user){
        this(user.getId(), user.getNombre(), user.getEmail(), user.getRol());

    }
}
