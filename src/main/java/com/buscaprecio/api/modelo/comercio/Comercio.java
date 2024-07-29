package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.Direccion;
import com.buscaprecio.api.modelo.oferta.Oferta;
import com.buscaprecio.api.modelo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Comercio")
@Table(name = "comercio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comercio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    private String abonado;
    private LocalDateTime fecha;

    @OneToMany
    @JoinColumn (name = "oferta_id")
    private List<Oferta> oferta;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User encargado;


}
