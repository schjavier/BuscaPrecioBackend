package com.buscaprecio.api.userTest;

import com.buscaprecio.api.modelo.user.DatosCrearUsuario;
import com.buscaprecio.api.modelo.user.DatosRespuestaUsuario;
import com.buscaprecio.api.modelo.user.User;
import com.buscaprecio.api.modelo.user.UserRepository;
import com.buscaprecio.api.servicios.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCrearUsuario() {




    }
}
