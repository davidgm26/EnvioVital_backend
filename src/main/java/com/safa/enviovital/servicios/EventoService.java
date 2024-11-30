package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.EventoRequestDto;
import com.safa.enviovital.dto.EventoResponseDto;
import com.safa.enviovital.excepciones.NotFoundException.EventoNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.ProvinciaDontHaveEventException;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.repositorios.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ProvinciaService provinciaService;



    public List<EventoResponseDto> getAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(EventoResponseDto::EventoResponseDtoFromEvento)
                .collect(Collectors.toList());
    }


    public List<EventoResponseDto> getAllActivos() {
        List<Evento> eventos = eventoRepository.findAllActives();
        return eventos.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());

    }

    public Evento getEventoById(int id) {
        return eventoRepository.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
    }

    public List<EventoResponseDto> getEventoByProvincia(int id) {
        List<Evento> events = eventoRepository.findByProvincia(provinciaService.getProvinciaById(id));
        if (events.isEmpty())
            throw new ProvinciaDontHaveEventException(provinciaService.getProvinciaById(id).getNombre());
        return events.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());
    }

    public Evento createEvento(EventoRequestDto eventoRequest) {
        Evento e = Evento.builder()
                .nombre(eventoRequest.getNombre())
                .descripcion(eventoRequest.getDescripcion())
                .provincia(provinciaService.getProvinciaById(eventoRequest.getIdProvincia()))
                .esActivo(true)
                .build();
        return eventoRepository.save(e);
    }

    public EventoResponseDto editarEvento(int id, EventoRequestDto eventoRequestDto) {
        Provincia provincia = provinciaService.getProvinciaById(eventoRequestDto.getIdProvincia());
        Evento evento = getEventoById(id);
        evento = EventoRequestDto.EventoFromRequest(eventoRequestDto, evento, provincia);
        return EventoResponseDto.EventoResponseDtoFromEvento(eventoRepository.save(evento));
    }

    public Evento cambiarEstadoEvento(int id) {
        Evento e = getEventoById(id);
        e.setEsActivo(!e.getEsActivo());
        return eventoRepository.save(e);
    }


    public void eliminarEvento(int id) {
        Evento e = getEventoById(id);
        e.getEventoAlmacenes().remove(e);
        eventoRepository.delete(e);
    }

}

