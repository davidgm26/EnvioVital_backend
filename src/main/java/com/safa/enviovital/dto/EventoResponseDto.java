package com.safa.enviovital.dto;


import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Evento;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class EventoResponseDto {

    private String nombre;
    private String descripcion;
    private boolean activo;
    private String provincia;
    private Set<EventoAlmacenDtoResponse> almacenes;


    public static EventoResponseDto EventoResponseDtoFromEvento(Evento evento) {
        return EventoResponseDto.builder()
                .descripcion(evento.getDescripcion())
                .nombre(evento.getNombre())
                .activo(evento.getEsActivo())
                .provincia(evento.getProvincia().getNombre())
                .almacenes(
                        evento.getEventoAlmacenes().stream()
                                .map(EventoAlmacenDtoResponse::toDto)
                                .collect(Collectors.toSet())
                )
                .build();
    }


}
