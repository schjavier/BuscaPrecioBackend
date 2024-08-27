package com.buscaprecio.api.servicios;

import com.buscaprecio.api.excepciones.comercio.ComercioExisteException;
import com.buscaprecio.api.excepciones.comercio.ComercioNotFoundException;
import com.buscaprecio.api.excepciones.user.UserNotFoundException;
import com.buscaprecio.api.modelo.comercio.Comercio;
import com.buscaprecio.api.modelo.comercio.DatosListadoComercios;
import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import com.buscaprecio.api.modelo.direccion.DatosDireccion;
import com.buscaprecio.api.modelo.direccion.Direccion;
import com.buscaprecio.api.repositorio.ComercioRepository;
import com.buscaprecio.api.repositorio.DireccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ComercioService implements IComercioService{

    @Autowired
    private ComercioRepository comercioRepository;


    @Transactional
    @Override
    public DatosRespuestaComercio crearComercio(DatosRegistrarComercio dataComercio) {

         if (comercioRepository.existsByNombreAndEncargado( dataComercio.nombre(), dataComercio.encargado() )){

            throw new ComercioExisteException("El Comercio ya se encuentra Registrado");

        }

        Comercio comercio = new Comercio(dataComercio);

        Direccion direccion = new Direccion(
                    dataComercio.direccion().getCalle(),
                    dataComercio.direccion().getBarrio(),
                    dataComercio.direccion().getProvincia()
                );

        comercio.setDireccion(direccion);
        comercio = comercioRepository.save(comercio);

        return new DatosRespuestaComercio(
                comercio.getId(),
                comercio.getNombre(),
                new DatosDireccion(direccion.getCalle(), direccion.getBarrio(), direccion.getProvincia()),
                comercio.getFechaAbono(),
                comercio.getEncargado()
        );

    }

    @Override
    public void eliminarComercio(Long id) {

        if(!comercioRepository.existsById(id)){

            throw new ComercioNotFoundException("El comercio con el id: " + id + " no existe");

        }

        comercioRepository.deleteById(id);
    }

    @Override
    public DatosRespuestaComercio mostrarComercio(Long id) {

        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow( () -> new ComercioNotFoundException("El Comercio no existe!") );


        return new DatosRespuestaComercio(
                comercio.getId(),
                comercio.getNombre(),
                new DatosDireccion(
                        comercio.getDireccion().getCalle(),
                        comercio.getDireccion().getBarrio(),
                        comercio.getDireccion().getProvincia()),
                comercio.getFechaAbono(),
                comercio.getEncargado()
        );
    }

    @Override
    public Page<DatosListadoComercios> listarComercios(Pageable paginacion) {
        return comercioRepository.findAll(paginacion).map(DatosListadoComercios::new);
    }
}
