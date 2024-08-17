package com.buscaprecio.api.controladores;
import com.buscaprecio.api.modelo.user.DatosCrearUsuario;
import com.buscaprecio.api.modelo.user.DatosListadoUsuarios;
import com.buscaprecio.api.modelo.user.DatosModificarUsuario;
import com.buscaprecio.api.modelo.user.DatosRespuestaUsuario;
import com.buscaprecio.api.servicios.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario (@RequestBody @Valid DatosCrearUsuario datosCrearUsuario ){
        return userService.crearUsuario(datosCrearUsuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        return userService.eliminarUsuario(id);
        }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> mostrarUsuario(@PathVariable Long id){
        return userService.mostrarUsuario(id);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuarios>> listarUsuarios(@PageableDefault(size=2) Pageable paginacion){
        return userService.listarUsuarios(paginacion);
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificarUsuario (@RequestBody @Valid DatosModificarUsuario datosModificarUsuario){
        return userService.modificarUsuario(datosModificarUsuario);
    }
}

