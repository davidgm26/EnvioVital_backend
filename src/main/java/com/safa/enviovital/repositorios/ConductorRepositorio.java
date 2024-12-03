package com.safa.enviovital.repositorios;

import com.safa.enviovital.dto.ConductorResponseDTO;
import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Conductor;
import com.safa.enviovital.modelos.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConductorRepositorio extends JpaRepository<Conductor, Integer> {

    @Query("SELECT new com.safa.enviovital.dto.ConductorResponseDTO(c.id, c.nombre, c.apellidos, c.dni, c.direccion, c.telefono, c.fechaNacimiento, c.email, c.usuario.id) " +
            "FROM Conductor c WHERE c.id = :id")
    Optional<ConductorResponseDTO> findConductorByIdWithDTO(Integer id);

    @Query("SELECT new com.safa.enviovital.dto.ConductorResponseDTO(c.id, c.nombre, c.apellidos, c.dni, c.direccion, c.telefono, c.fechaNacimiento, c.email, c.usuario.id) " +
            "FROM Conductor c")
    List<ConductorResponseDTO> findAllConductoresWithDTO();

    //GET /conductores/usuario/{idUsuario}  hazlo sin dto
    @Query("SELECT c FROM Conductor c WHERE c.usuario.id = :idUsuario")
    Optional<Conductor> findConductorByUsuarioId(Integer idUsuario);

    @Query("SELECT c FROM Conductor c JOIN c.usuario u ON c.usuario.id = u.id WHERE u.username = :username")
    Optional<Conductor> findConductorByUsername(String username);




}
