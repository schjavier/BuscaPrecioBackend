package com.buscaprecio.api.modelo.comercio;

import com.buscaprecio.api.modelo.direccion.Direccion;
import com.buscaprecio.api.modelo.oferta.Oferta;
import com.buscaprecio.api.modelo.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Comercio")
@Table(name = "comercio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comercio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    private String abonado;
    @Column(name = "fecha_abono")
    private LocalDateTime fechaAbono;

    @OneToMany
    @JoinColumn (name = "oferta_id")
    private List<Oferta> ofertas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User encargado;

    public Comercio(DatosRegistrarComercio datos){
        this.nombre = datos.nombre();
        this.direccion = datos.direccion();
        this.abonado = "No Abonado";
        this.fechaAbono = LocalDateTime.now();
        this.encargado = datos.encargado();


    }


}
