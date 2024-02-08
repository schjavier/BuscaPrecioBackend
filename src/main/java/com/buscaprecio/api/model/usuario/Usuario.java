package com.buscaprecio.api.model.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table (name = "users")
@Entity (name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")

public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;
    private String provincia;
    private String ciudad;
    private String barrio;


}
