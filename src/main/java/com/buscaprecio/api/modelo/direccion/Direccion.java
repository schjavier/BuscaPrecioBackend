package com.buscaprecio.api.modelo.direccion;

import jakarta.persistence.*;
import lombok.*;

@Entity (name= "Direcion")
@Table (name = "direccion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Direccion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private String barrio;
    private String provincia;

    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.barrio = datosDireccion.barrio();
        this.provincia = datosDireccion.provincia();
    }
}
