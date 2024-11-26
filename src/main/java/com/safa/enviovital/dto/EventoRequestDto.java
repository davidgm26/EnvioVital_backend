package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Evento;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequestDto {

    private String nombre;
    private String descripcion;
    private int idProvincia;
    private String nombreProvincia;
    private String fotoUrl;

    public static EventoRequestDto TarjetaEventoInicio (Evento evento) {
        return EventoRequestDto.builder()
                .nombre(evento.getNombre())
                .descripcion(evento.getDescripcion())
                .idProvincia(evento.getId())
                .nombreProvincia(evento.getProvincia().getNombre())
                .build();

    }








}
