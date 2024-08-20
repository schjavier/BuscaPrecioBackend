package com.buscaprecio.api.servicios;

import com.buscaprecio.api.excepciones.ComercioNotFoundException;
import com.buscaprecio.api.excepciones.UserNotFoundException;
import com.buscaprecio.api.modelo.comercio.Comercio;
import com.buscaprecio.api.modelo.comercio.DatosListadoComercios;
import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import com.buscaprecio.api.modelo.direccion.DatosDireccion;
import com.buscaprecio.api.modelo.direccion.Direccion;
import com.buscaprecio.api.repositorio.ComercioRepository;
import com.buscaprecio.api.repositorio.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ComercioService implements IComercioService{

    @Autowired
    private ComercioRepository comercioRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public ResponseEntity<DatosRespuestaComercio> crearComercio(DatosRegistrarComercio dataComercio) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        //exeptions aqui

        Comercio comercio = comercioRepository.save(new Comercio(dataComercio));
        DatosDireccion datosDireccion = new DatosDireccion(
                dataComercio.direccion().getCalle(),
                dataComercio.direccion().getBarrio(),
                dataComercio.direccion().getProvincia());

        direccionRepository.save(new Direccion(datosDireccion));

        DatosRespuestaComercio datosRespuestaComercio = new DatosRespuestaComercio(
                comercio.getId(),
                comercio.getNombre(),
                datosDireccion,
                comercio.getFecha(),
                comercio.getEncargado()
        );

        URI url = uriComponentsBuilder.path("comercio/{id}").buildAndExpand(comercio.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaComercio);
    }

    @Override
    public ResponseEntity eliminarComercio(Long id) {

        if(!comercioRepository.existsById(id)){

            throw new UserNotFoundException("El comercio con el id: " + id + " no existe");

        }

        comercioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DatosRespuestaComercio> mostrarComercio(Long id) {

        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow( () -> new ComercioNotFoundException("El Comercio no existe!") );


        DatosRespuestaComercio datosRespuesta = new DatosRespuestaComercio(
                comercio.getId(),
                comercio.getNombre(),
                new DatosDireccion(
                        comercio.getDireccion().getCalle(),
                        comercio.getDireccion().getBarrio(),
                        comercio.getDireccion().getProvincia()),
                comercio.getFecha(),
                comercio.getEncargado()
        );

        return ResponseEntity.ok(datosRespuesta);
    }

    @Override
    public ResponseEntity<Page<DatosListadoComercios>> listarComercios(Pageable paginacion) {
        return ResponseEntity.ok(comercioRepository.findAll(paginacion).map(DatosListadoComercios::new));
    }
}
