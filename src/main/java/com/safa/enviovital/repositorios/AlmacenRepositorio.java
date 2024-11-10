package com.safa.enviovital.repositorios;

import com.safa.enviovital.dto.AlmacenResponseDTO;
import com.safa.enviovital.modelos.Almacen;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlmacenRepositorio extends JpaRepository<Almacen, Integer> {

    @Query("SELECT new com.safa.enviovital.dto.AlmacenResponseDTO(a.id, a.nombre, a.descripcion, a.direccion, a.email, a.esActivo, a.provincia.id, a.usuario.id) FROM Almacen a")
    List<AlmacenResponseDTO> findAllAlmacenesWithDTO();

    @Query("SELECT new com.safa.enviovital.dto.AlmacenResponseDTO(a.id, a.nombre, a.descripcion, a.direccion, a.email, a.esActivo, a.provincia.id, a.usuario.id) FROM Almacen a WHERE a.id = :id")
    Optional<AlmacenResponseDTO> findAlmacenByIdWithDTO(Integer id);




}
