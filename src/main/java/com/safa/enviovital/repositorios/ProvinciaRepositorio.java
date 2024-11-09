package com.safa.enviovital.repositorios;

import com.safa.enviovital.dto.ProvinciaResponseDTO;
import com.safa.enviovital.modelos.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProvinciaRepositorio extends JpaRepository<Provincia, Integer> {

    @Query("SELECT new com.safa.enviovital.dto.ProvinciaResponseDTO(p.id, p.nombre) FROM Provincia p")
    List<ProvinciaResponseDTO> findAllProvinciasDTO();

    @Query("SELECT new com.safa.enviovital.dto.ProvinciaResponseDTO(p.id, p.nombre) FROM Provincia p WHERE p.id = :id")
    Optional<ProvinciaResponseDTO> findProvinciaById(Integer id);
}
