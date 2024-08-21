package com.buscaprecio.api.userTest;


import com.buscaprecio.api.excepciones.user.UserExisteException;
import com.buscaprecio.api.excepciones.user.UserNotFoundException;
import com.buscaprecio.api.modelo.user.*;
import com.buscaprecio.api.repositorio.UserRepository;
import com.buscaprecio.api.servicios.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
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
    public void debeLanzarUserExisteExceptionSiUserExiste(){
        DatosCrearUsuario datosDeEntrada = new DatosCrearUsuario(
                "javier",
                "javier@123.com",
                "12345567",
                "ADMIN");

        User userExistente = new User(datosDeEntrada);
        userExistente.setId(1L);

        when(userRepository.existsByEmail(datosDeEntrada.email())).thenReturn(true);

        assertThrows(UserExisteException.class, () -> userService.crearUsuario(datosDeEntrada));
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

        when(userRepository.existsById(1L)).thenReturn(true);

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
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        ResponseEntity<DatosRespuestaUsuario> respuesta = userService.mostrarUsuario(1L);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuestaEsperada, respuesta.getBody());
        verify(userRepository).findById(user.getId());


    }

    //Metodo mostrar un solo usuario del UserService
    @Test
    public void debeLanzarUserNotFoundExceptionCuandoIdNoValido(){

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.mostrarUsuario(1L));

    }

    //Metodo eliminar usuario
    @Test
    public void debeLanzarUserNotFoundExceptionAlBorrarSiUserNoExiste(){

        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> userService.eliminarUsuario(1L));

    }

    @Test
    public void debeListarUsuariosConPaginacion(){
        User user1 = new User(new DatosCrearUsuario(
                "Alicia",
                "alicia@123.com",
                "6666666",
                "ADMIN"));

        user1.setId(1L);

        User user2 = new User(new DatosCrearUsuario(
                "Javier",
                "javier@123.com",
                "00000000",
                "USER"));

        user2.setId(2L);

        List<User> usuarios = List.of(user1, user2);

        Pageable paginacion = PageRequest.of(0 , 10);

        Page<User> pagina = new PageImpl<>(usuarios, paginacion, usuarios.size());

        when(userRepository.findAll(paginacion)).thenReturn(pagina);

        ResponseEntity<Page<DatosListadoUsuarios>> respuesta = userService.listarUsuarios(paginacion);

//        Chequea el codigo de la respuesta que sea el esperado
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
//        chequea que el total de elementos sea el esperado
        assertEquals(2, respuesta.getBody().getTotalElements());
//        Cheque que el total de las paginas sea el esperado
        assertEquals(1, respuesta.getBody().getTotalPages());
//        chequea que el tamaño del contenido sea el esperado
        assertEquals(2, respuesta.getBody().getContent().size());

//        chequea que la llamada al repositorio se esta haciendo de forma correcta
        verify(userRepository).findAll(paginacion);
    }

    @Test
    public void debeModificarUsuarioSiIdValido(){
        DatosModificarUsuario datosModificarUsuario = new DatosModificarUsuario(
                1L,
                "Pedro",
                "pedro@123.com",
                "1234567",
                "ADMIN"
        );

        when(userRepository.existsById(datosModificarUsuario.id())).thenReturn(true);

        User usuarioSinModificar = new User(
                1L,
                "pedro",
                "peter@123.com",
                "1234567",
                "ADMIN",
                new ArrayList<>());

        when(userRepository.getReferenceById(datosModificarUsuario.id())).thenReturn(usuarioSinModificar);

        when(userRepository.save(usuarioSinModificar)).thenReturn(usuarioSinModificar);

        DatosRespuestaUsuario respuestaEsperada = new DatosRespuestaUsuario(
                1L,
                "Pedro",
                "pedro@123.com",
                "ADMIN"
        );

        ResponseEntity respuesta = userService.modificarUsuario(datosModificarUsuario);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(respuestaEsperada, respuesta.getBody());

        verify(userRepository).existsById(datosModificarUsuario.id());
        verify(userRepository).getReferenceById(datosModificarUsuario.id());
        verify(userRepository).save(usuarioSinModificar);
    }

    @Test
    public void debeArrojarUserNotFoundExceptionAlModificarSiUserNoExiste(){

        DatosModificarUsuario datosModificarUsuario = new DatosModificarUsuario(
                1L,
                "Pedro",
                "pedro@123.com",
                "1234567",
                "ADMIN"
        );

        when(userRepository.existsById(datosModificarUsuario.id())).thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> userService.modificarUsuario(datosModificarUsuario) );

        // verifica que no hay ninguna llamada al repositorio, despues de devolcer la exception.
        verify(userRepository, never()).getReferenceById(datosModificarUsuario.id());
        verify(userRepository, never()).save(any());

    }

}
