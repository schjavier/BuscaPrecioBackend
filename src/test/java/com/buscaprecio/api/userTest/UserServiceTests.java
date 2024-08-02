package com.buscaprecio.api.userTest;


import ch.qos.logback.core.recovery.ResilientFileOutputStream;
import com.buscaprecio.api.modelo.user.*;
import com.buscaprecio.api.servicios.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void debeCrearUsuarioSatisfactoriamente() {
        DatosCrearUsuario datosDeEntrada = new DatosCrearUsuario(
                "javier",
                "javier@123.com",
                "12345567",
                "ADMIN");

        User usuarioSinId = new User(datosDeEntrada);
        User usuarioConId = new User(datosDeEntrada);
        usuarioConId.setId(1L);

        DatosRespuestaUsuario respuestaEsperada = new DatosRespuestaUsuario (1L, "javier",
                "javier@123.com",
                "ADMIN");

        when(userRepository.save(usuarioSinId)).thenReturn(usuarioConId);

        ResponseEntity<DatosRespuestaUsuario> respuesta = userService.crearUsuario(datosDeEntrada);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertEquals(respuestaEsperada, respuesta.getBody());
        verify(userRepository).save(usuarioSinId);
    }

    @Test
    public void debeEliminarUsuarioCuandoIdValido(){
        DatosCrearUsuario datosDeEntrada = new DatosCrearUsuario(
                "javier",
                "javier@123.com",
                "12345567",
                "ADMIN");

        User user = new User(datosDeEntrada);
        user.setId(1L);


        ResponseEntity respuesta = userService.eliminarUsuario(user.getId());

        assertEquals(HttpStatus.NO_CONTENT, respuesta.getStatusCode());
        verify(userRepository).deleteById(user.getId());
    }


    @Test
    public void debeMostrarUnUsuarioCuandoIdValido(){
        DatosCrearUsuario datosDeEntrada = new DatosCrearUsuario(
                "javier",
                "javier@123.com",
                "12345567",
                "ADMIN");

        User user = new User(datosDeEntrada);
        user.setId(1L);

        DatosRespuestaUsuario respuestaEsperada = new DatosRespuestaUsuario(
                1L,
                "javier",
                "javier@123.com",
                "ADMIN"
        );
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);

        ResponseEntity<DatosRespuestaUsuario> respuesta = userService.mostrarUsuario(1L);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuestaEsperada, respuesta.getBody());
        verify(userRepository).getReferenceById(user.getId());


    }




}
