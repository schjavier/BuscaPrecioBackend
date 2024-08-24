package com.buscaprecio.api.controladores;

import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import com.buscaprecio.api.servicios.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
