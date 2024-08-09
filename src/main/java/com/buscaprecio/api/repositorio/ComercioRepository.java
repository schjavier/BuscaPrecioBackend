package com.buscaprecio.api.repositorio;

import com.buscaprecio.api.modelo.comercio.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercioRepository extends JpaRepository<Comercio, Long> {

}
