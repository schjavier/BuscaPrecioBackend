package com.buscaprecio.api.repositorio;

import com.buscaprecio.api.modelo.direccion.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
