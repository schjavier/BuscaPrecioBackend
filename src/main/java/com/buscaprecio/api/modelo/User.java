package com.buscaprecio.api.modelo;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String email;
    private String password;
    private String rol;

}
