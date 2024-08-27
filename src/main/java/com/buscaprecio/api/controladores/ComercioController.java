package com.buscaprecio.api.controladores;

import com.buscaprecio.api.modelo.comercio.DatosListadoComercios;
import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import com.buscaprecio.api.servicios.ComercioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/comercio")
public class ComercioController {

    @Autowired
    ComercioService comercioService;

    @PostMapping("/new")
    public ResponseEntity<DatosRespuestaComercio> crearComercio(@RequestBody DatosRegistrarComercio datosRegistrarComercio,
                                                                UriComponentsBuilder uriComponentsBuilder){

        DatosRespuestaComercio respuestaComercio = comercioService.crearComercio(datosRegistrarComercio);

        URI url = uriComponentsBuilder.path("/comercio/id")
                .buildAndExpand(respuestaComercio.id())
                .toUri();

        return ResponseEntity.created(url).body(respuestaComercio);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarComercio(@PathVariable Long id){
        comercioService.eliminarComercio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaComercio> mostrarComercio(@PathVariable Long id){
        DatosRespuestaComercio datosRespuesta = comercioService.mostrarComercio(id);
        return ResponseEntity.ok(datosRespuesta);

    }

    @GetMapping("/all")
    public ResponseEntity<Page<DatosListadoComercios>> listarUsuarios(@PageableDefault(size=3) Pageable paginacion){
        Page<DatosListadoComercios> paginas = comercioService.listarComercios(paginacion);
        return ResponseEntity.ok(paginas);

    }



}
