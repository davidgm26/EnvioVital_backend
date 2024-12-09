package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.EventoAlmacenConductor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class EventoAlmacenConductorDto {

    private EventoAlmacenDtoResponse eventoAlmacenDtoResponse;
    private int idConductor;



    public static EventoAlmacenConductorDto fromEntity(EventoAlmacenConductor eventoAlmacenConductor){
        return EventoAlmacenConductorDto.builder()
                .eventoAlmacenDtoResponse(EventoAlmacenDtoResponse.toDto(eventoAlmacenConductor.getEventoAlmacen()))
                .idConductor(eventoAlmacenConductor.getConductor().getId())
                .build();
    }



}
