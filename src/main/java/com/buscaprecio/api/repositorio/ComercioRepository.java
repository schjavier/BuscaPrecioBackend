package com.buscaprecio.api.repositorio;

import com.buscaprecio.api.modelo.comercio.Comercio;
import com.buscaprecio.api.modelo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercioRepository extends JpaRepository<Comercio, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByEncargado(User encargado);
}
