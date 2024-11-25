package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.AlmacenResponseDTO;
import com.safa.enviovital.dto.EventoRequestDto;
import com.safa.enviovital.dto.EventoResponseDto;
import com.safa.enviovital.excepciones.NotFoundException.EventoNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.ProvinciaDontHaveEventException;
import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.repositorios.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return eventos.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());
    }


    public List<EventoResponseDto>getAllActivos(){
        List<Evento> eventos = eventoRepository.findAllActives();
        return eventos.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());

    }

    public Evento getEventoById(int id){
        return eventoRepository.findById(id).orElseThrow( () -> new EventoNotFoundException(id));
    }

    public List<EventoResponseDto> getEventoByProvincia(int id){
       List<Evento> events = eventoRepository.findByProvincia( provinciaService.getProvinciaById(id));
       if (events.isEmpty())
           throw new ProvinciaDontHaveEventException(provinciaService.getProvinciaById(id).getNombre());
        return events.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());
    }

    public Evento createEvento(EventoRequestDto eventoRequest){
        Evento e = Evento.builder()
                .nombre(eventoRequest.getNombre())
                .descripcion(eventoRequest.getDescripcion())
                .provincia(provinciaService.getProvinciaById(eventoRequest.getIdProvincia()))
                .esActivo(true)
                .build();
        return eventoRepository.save(e);
    }

    public EventoResponseDto editarEvento(int id, EventoRequestDto eventoRequestDto){
        Evento event = eventoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        event.setNombre(eventoRequestDto.getNombre());
        event.setDescripcion(eventoRequestDto.getDescripcion());
        event.setProvincia(provinciaService.getProvinciaById(eventoRequestDto.getIdProvincia()));
        return EventoResponseDto.EventoResponseDtoFromEvento(eventoRepository.save(event));
    }

//
//    public void eliminarEvento(int id){
//        Evento e = eventoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        e.getAlmacenes().remove(e);
//        eventoRepository.delete(e);
//    }



}
