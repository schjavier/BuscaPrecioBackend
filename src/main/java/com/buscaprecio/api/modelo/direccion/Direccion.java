package com.buscaprecio.api.modelo.direccion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity (name= "Direcion")
@Table (name = "direccion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Direccion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private String barrio;
    private String provincia;

}
