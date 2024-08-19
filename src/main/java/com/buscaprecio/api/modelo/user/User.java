package com.buscaprecio.api.modelo.user;


import com.buscaprecio.api.modelo.comercio.Comercio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;
    private String rol;

    @OneToMany(mappedBy = "encargado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comercio> comercios = new ArrayList<>();

    public User(DatosCrearUsuario dataUser) {
        this.nombre = dataUser.nombre();
        this.email = dataUser.email();
        this.password = dataUser.password();
        this.rol = dataUser.rol();
    }

    public void actualizarDatos(DatosModificarUsuario dataUser) {
        this.id = dataUser.id();
        this.nombre = dataUser.nombre();
        this.email = dataUser.email();
        this.password = dataUser.password();
        this.rol = dataUser.rol();

    }

    public void addComercio(Comercio comercio){
        this.comercios.add(comercio);
        comercio.setEncargado(this);
    }

    public void removeComercios(Comercio comercio){
        this.comercios.remove(comercio);
        comercio.setEncargado(null);
    }

}
