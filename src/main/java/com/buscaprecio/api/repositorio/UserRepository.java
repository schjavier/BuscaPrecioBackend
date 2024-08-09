package com.buscaprecio.api.repositorio;

import com.buscaprecio.api.modelo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail();
}
