package com.safa.enviovital.repositorios;


import com.safa.enviovital.dto.AlmacenResponseDTO;
import com.safa.enviovital.dto.EventoAlmacenDtoResponse;
import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.EventoAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoAlmacenRepositorio extends JpaRepository<EventoAlmacen, Integer> {
    Optional<EventoAlmacen> findByEventoAndAlmacen(Evento evento, Almacen almacen);

    @Query("SELECT new com.safa.enviovital.dto.EventoAlmacenDtoResponse(" +
            "ea.evento.id, " +
            "ea.almacen.id, " +
            "ea.almacen.nombre, " +
            "ea.evento.nombre) " +
            "FROM EventoAlmacen ea " +
            "WHERE ea.evento.id = :idEvento")
    List<EventoAlmacenDtoResponse> findEventoAlmacenByEventoId(@Param("idEvento") Integer idEvento);
}

