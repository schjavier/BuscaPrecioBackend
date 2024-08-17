package com.buscaprecio.api.modelo.user;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "user")
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
}
