package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.EventoAlmacen;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoAlmacenDtoResponse {
    private Integer id;
    private Integer idEvento;
    private Integer idAlmacen;
    private String nombreAlmacen;
    private String nombreEvento;
    private AlmacenResponseDTO almacen;


    public static EventoAlmacenDtoResponse toDto(EventoAlmacen eventoAlmacen) {
        return EventoAlmacenDtoResponse.builder()
                .id(eventoAlmacen.getId())
                .idEvento(eventoAlmacen.getEvento().getId())
                .idAlmacen(eventoAlmacen.getAlmacen().getId())
                .nombreAlmacen(eventoAlmacen.getAlmacen().getNombre())
                .nombreEvento(eventoAlmacen.getEvento().getNombre())
                .almacen(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(eventoAlmacen.getAlmacen()))
                .build();
    }
}
