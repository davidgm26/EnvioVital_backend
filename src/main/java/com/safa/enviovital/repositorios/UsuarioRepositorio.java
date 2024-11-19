package com.safa.enviovital.repositorios;

import com.safa.enviovital.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);

    Optional <Usuario> findTopByUsername(String username);

}
