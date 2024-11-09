package com.safa.enviovital.repositorios;


import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.EventoAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoAlmacenRepositorio extends JpaRepository<EventoAlmacen, Integer> {
    Optional<EventoAlmacen> findByEventoAndAlmacen(Evento evento, Almacen almacen);

}

