package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.user.DatosCrearUsuario;
import com.buscaprecio.api.modelo.user.DatosRespuestaUsuario;
import com.buscaprecio.api.modelo.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IUserService {

    ResponseEntity<DatosRespuestaUsuario> crearUsuario(DatosCrearUsuario dataUser);



}
