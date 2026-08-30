package com.logitrack.backend.repository;

import com.logitrack.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // solo con escribir el nombre del metodo asi, spring hace la consulta SQL por debajo automáticamente.
    Optional<Usuario> findByUsername(String username);
}