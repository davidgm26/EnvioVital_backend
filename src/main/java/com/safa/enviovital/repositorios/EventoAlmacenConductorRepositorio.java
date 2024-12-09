package com.safa.enviovital.repositorios;


import com.safa.enviovital.modelos.EventoAlmacenConductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoAlmacenConductorRepositorio extends JpaRepository<EventoAlmacenConductor, Integer> {

    Optional<EventoAlmacenConductor> findByEventoAlmacenIdAndConductorId(Integer eventoAlmacenId, Integer conductorId);

    List<EventoAlmacenConductor> findEventoAlmacenConductorByConductorId(Integer conductorId);

    EventoAlmacenConductor findEventoAlmacenConductorByConductorIdAndEventoAlmacenId(@Param("conductorId") Integer conductorId, @Param("eventoAlmacenId") Integer eventoAlmacenId);

    List<EventoAlmacenConductor> findByEventoAlmacenId_AlmacenId(Integer almacenId);



}
