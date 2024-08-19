package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.comercio.DatosListadoComercios;
import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IComercioService {

    ResponseEntity<DatosRespuestaComercio> crearComercio(DatosRegistrarComercio dataComercio);
    ResponseEntity eliminarComercio(Long id);
    ResponseEntity<DatosRespuestaComercio> mostrarComercio(Long id);
    ResponseEntity<Page<DatosListadoComercios>> listarComercios(Pageable paginacion);


}
