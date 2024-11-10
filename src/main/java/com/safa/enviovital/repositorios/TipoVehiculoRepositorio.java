package com.safa.enviovital.repositorios;

import com.safa.enviovital.dto.TipoVehiculoResponseDTO;
import com.safa.enviovital.modelos.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TipoVehiculoRepositorio extends JpaRepository<TipoVehiculo, Integer> {

    @Query("SELECT new com.safa.enviovital.dto.TipoVehiculoResponseDTO(tv.id, tv.nombre) FROM TipoVehiculo tv")
    List<TipoVehiculoResponseDTO> findAllTipoVehiculoResponseDTO();


    @Query("SELECT new com.safa.enviovital.dto.TipoVehiculoResponseDTO(tv.id, tv.nombre) FROM TipoVehiculo tv WHERE tv.id = :id")
    Optional<TipoVehiculoResponseDTO> findTipoVehiculoById(Integer id);
}