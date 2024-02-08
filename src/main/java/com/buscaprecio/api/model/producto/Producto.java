package com.buscaprecio.api.model.producto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "productos")
@Entity (name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")

public class Producto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Float precio;
    private LocalDateTime fecha = LocalDateTime.now();
}
