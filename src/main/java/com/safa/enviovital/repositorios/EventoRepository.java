package com.safa.enviovital.repositorios;

import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.Provincia;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @EntityGraph(
            value = "Evento.almacenes",
            type = EntityGraph.EntityGraphType.FETCH
    )
    List<Evento> findAll();

    List<Evento> findByProvincia(Provincia provincia);



}
