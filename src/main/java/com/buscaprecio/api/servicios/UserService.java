package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.user.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<DatosRespuestaUsuario> crearUsuario( DatosCrearUsuario dataUser ) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        User user = userRepository.save(new User(dataUser));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(

                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol());

        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @Override
    public ResponseEntity eliminarUsuario(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DatosRespuestaUsuario> mostrarUsuario(Long id) {
        User user = userRepository.getReferenceById(id);
        DatosRespuestaUsuario datosRespuesta = new DatosRespuestaUsuario(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol());

        return ResponseEntity.ok(datosRespuesta);
    }

    @Override
    public ResponseEntity<Page<DatosListadoUsuarios>> listarUsuarios(Pageable paginacion) {
        return ResponseEntity.ok(userRepository.findAll(paginacion).map(DatosListadoUsuarios::new));
    }
}
