package com.buscaprecio.api.repositorio;

import com.buscaprecio.api.modelo.oferta.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertasRepository extends JpaRepository<Oferta, Long> {
}
