package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.EventoResponseDto;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.repositorios.EventoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<EventoResponseDto> getAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());
    }

    public Evento getEventoById(int id){
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento editarEvento(int id, Evento evento){
        Evento event = getEventoById(id);
        event.setId(id);
        event.setNombre(evento.getNombre());
        event.setDescripcion(evento.getDescripcion());
        event.setProvincia(event.getProvincia());
        return event;
    }

    public Evento createEvento(Evento evento){
        return eventoRepository.save(evento);
    }


}
