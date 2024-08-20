package com.buscaprecio.api.modelo.oferta;

import com.buscaprecio.api.modelo.comercio.Comercio;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Oferta")
@Table (name = "oferta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Oferta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Float precio;

    @ManyToOne
    @JoinColumn(name = "comercio_id")
    Comercio comercio;



}

