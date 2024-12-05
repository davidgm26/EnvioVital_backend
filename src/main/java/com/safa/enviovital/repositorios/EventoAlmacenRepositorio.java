package com.safa.enviovital.repositorios;


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


    @Query("SELECT ea FROM EventoAlmacen ea WHERE ea.evento.id = :idEvento AND ea.almacen.esActivo = true")
    List<EventoAlmacen> findEventoAlmacenByEventoIdAndEsActivo(@Param("idEvento") Integer idEvento);

    List<EventoAlmacen> findEventoAlmacenByAlmacenId(@Param("idAlmacen") Integer idAlmacen);

    Optional <EventoAlmacen> findEventoAlmacenByAlmacenIdAndEventoId(@Param("idAlmacen") Integer idAlmacen, @Param("idEvento") Integer idEvento);

    List<EventoAlmacen> findAllByEventoId(Integer eventoAlmacenId);
}

