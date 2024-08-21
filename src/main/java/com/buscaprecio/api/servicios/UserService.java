package com.buscaprecio.api.servicios;

import com.buscaprecio.api.excepciones.user.UserExisteException;
import com.buscaprecio.api.excepciones.user.UserNotFoundException;
import com.buscaprecio.api.modelo.user.*;
import com.buscaprecio.api.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if (userRepository.existsByEmail(dataUser.email())){

            throw new UserExisteException("Existe un usuario registrado con ese Email: " + dataUser.email());

        }

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

        if(!userRepository.existsById(id)){

            throw new UserNotFoundException("El usuario con el id: " + id + " no existe.");

        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DatosRespuestaUsuario> mostrarUsuario(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("El Usuario No Existe!"));

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

    @Override
    public ResponseEntity modificarUsuario(DatosModificarUsuario dataUser) {

        if(!userRepository.existsById(dataUser.id())){

            throw new UserNotFoundException("No existe el usuario con ese ID");

        }

        User usuario = userRepository.getReferenceById(dataUser.id());

        usuario.actualizarDatos(dataUser);

        userRepository.save(usuario);

        return ResponseEntity.ok(new DatosRespuestaUsuario(
                dataUser.id(),
                dataUser.nombre(),
                dataUser.email(),
                dataUser.rol()));
    }
}
