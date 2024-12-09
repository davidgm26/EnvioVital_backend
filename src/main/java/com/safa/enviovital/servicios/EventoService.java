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



import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ProvinciaService provinciaService;


    /**
     * Metodo para recuperar de base de datos todos los eventos que existen
     * @return lista con los eventos convertidos a DTO
     */
    public List<EventoResponseDto> getAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(EventoResponseDto::EventoResponseDtoFromEvento)
                .collect(Collectors.toList());
    }


    /**
     * Metodo para recuperar de base de datos todos los eventos activos que existen
     * @return lista con los eventos activos convertidos a DTO
     */
    public List<EventoResponseDto> getAllActivos() {
        List<Evento> eventos = eventoRepository.findAllActives();
        return eventos.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());

    }

    /**
     *  Metodo para buscar un evento específico por su id
     * @param id
     * @return devuelve el evento buscado
     */
    public Evento getEventoById(int id) {
        return eventoRepository.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
    }

    /**
     *  Metodo para filtrar eventos por provincias
     * @param id
     * @return
     */
    public List<EventoResponseDto> getEventoByProvincia(int id) {
        List<Evento> events = eventoRepository.findByProvincia(provinciaService.getProvinciaById(id));
        if (events.isEmpty())
            throw new ProvinciaDontHaveEventException(provinciaService.getProvinciaById(id).getNombre());
        return events.stream().map(EventoResponseDto::EventoResponseDtoFromEvento).collect(Collectors.toList());
    }

    /**
     *  Metodo para crear un evento.
     * @param eventoRequest, datos para la creacion del evento
     * @return guardado en base de datos del evento
     */
    public Evento createEvento(EventoRequestDto eventoRequest) {
        Evento e = Evento.builder()
                .nombre(eventoRequest.getNombre())
                .descripcion(eventoRequest.getDescripcion())
                .provincia(provinciaService.getProvinciaById(eventoRequest.getIdProvincia()))
                .esActivo(true)
                .build();
        return eventoRepository.save(e);
    }

    /**
     * Metodo para actualizar la información de un evento.
     *
     * @param id
     * @param eventoRequestDto
     * @return Actulizacion del evento con la información pasada por parametros.
     */
    public EventoResponseDto editarEvento(int id, EventoRequestDto eventoRequestDto) {
        Provincia provincia = provinciaService.getProvinciaById(eventoRequestDto.getIdProvincia());
        Evento evento = getEventoById(id);
        evento = EventoRequestDto.EventoFromRequest(eventoRequestDto, evento, provincia);
        return EventoResponseDto.EventoResponseDtoFromEvento(eventoRepository.save(evento));
    }

    /**
     * Metodo para cambiar el estado de un evento
     *
     * @param id
     * @return Actulizacion del evento con el estado contrario al que tiene.
     */
    public Evento cambiarEstadoEvento(int id) {
        Evento e = getEventoById(id);
        e.setEsActivo(!e.getEsActivo());
        return eventoRepository.save(e);
    }

    /**
     * Método para eliminar un evento.
     *
     * @param id ID del evento a eliminar
     * @return Respuesta con el mensaje de eliminación
     */
    public void eliminarEvento(int id) {
        Evento e = getEventoById(id);
        e.getEventoAlmacenes().remove(e);
        eventoRepository.delete(e);
    }

}

