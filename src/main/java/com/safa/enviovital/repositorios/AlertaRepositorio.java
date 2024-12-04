package com.safa.enviovital.repositorios;

import com.safa.enviovital.modelos.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepositorio extends JpaRepository<Alerta, Integer> {
    List<Alerta> findByUsuarioIdAndVistaFalse(Integer usuarioId);
}