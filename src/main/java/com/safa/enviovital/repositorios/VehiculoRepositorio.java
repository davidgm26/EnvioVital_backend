package com.safa.enviovital.repositorios;


import com.safa.enviovital.modelos.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer> {

    Optional<Vehiculo> findVehiculoById(Integer id);
}

