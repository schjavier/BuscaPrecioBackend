package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.comercio.DatosListadoComercios;
import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class ComercioService implements IComercioService{


    @Override
    public ResponseEntity<DatosRespuestaComercio> crearComercio(DatosRegistrarComercio dataComercio) {
        return null;
    }

    @Override
    public ResponseEntity eliminarComercio(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DatosRespuestaComercio> mostrarComercio(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<DatosListadoComercios>> listarComercios(Pageable paginacion) {
        return null;
    }
}
