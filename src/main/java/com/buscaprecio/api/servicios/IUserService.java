package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.user.DatosCrearUsuario;
import com.buscaprecio.api.modelo.user.DatosListadoUsuarios;
import com.buscaprecio.api.modelo.user.DatosRespuestaUsuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IUserService {

    ResponseEntity<DatosRespuestaUsuario> crearUsuario(DatosCrearUsuario dataUser);
    ResponseEntity eliminarUsuario(Long id);
    ResponseEntity<DatosRespuestaUsuario> mostrarUsuario(Long id);
    ResponseEntity<Page<DatosListadoUsuarios>> listarUsuarios(Pageable paginacion);



}
