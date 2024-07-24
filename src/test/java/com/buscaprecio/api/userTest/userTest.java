package com.buscaprecio.api.userTest;

import com.buscaprecio.api.modelo.user.DatosCrearUsuario;
import com.buscaprecio.api.modelo.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class userTest {
    @Test
    public void testCrearUsuario() {
        DatosCrearUsuario datosCrearUsuario = new DatosCrearUsuario("javier",
                "123@123.com",
                "123123",
                "Admin");
        User user = new User(datosCrearUsuario);
        assertEquals(user.getNombre(), datosCrearUsuario.nombre());
        assertEquals(user.getEmail(), datosCrearUsuario.email());
        assertEquals(user.getPassword(), datosCrearUsuario.password());
        assertEquals(user.getRol(), datosCrearUsuario.rol());
    }
}
