package com.buscaprecio.api.servicios;

import com.buscaprecio.api.modelo.comercio.DatosListadoComercios;
import com.buscaprecio.api.modelo.comercio.DatosRegistrarComercio;
import com.buscaprecio.api.modelo.comercio.DatosRespuestaComercio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IComercioService {

    DatosRespuestaComercio crearComercio(DatosRegistrarComercio dataComercio);
    void eliminarComercio(Long id);
    DatosRespuestaComercio mostrarComercio(Long id);
    Page<DatosListadoComercios> listarComercios(Pageable paginacion);


}
