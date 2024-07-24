package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.user.DatosCrearUsuario;
import com.buscaprecio.api.modelo.user.DatosRespuestaUsuario;
import com.buscaprecio.api.modelo.user.User;
import com.buscaprecio.api.modelo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<DatosRespuestaUsuario> crearUsuario(DatosCrearUsuario dataUser,
                                                              UriComponentsBuilder uriComponentsBuilder) {

        User user = userRepository.save(new User(dataUser));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(

                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRol());

        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }
}
