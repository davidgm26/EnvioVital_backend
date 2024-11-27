package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.Provincia;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinciaEventoDto {

    private Integer id;
    private String nombre;
    private boolean activo;
    private String provincia;

    public static ProvinciaEventoDto filtroprovinciaevento (Evento evento) {
        return ProvinciaEventoDto.builder()
                .id(evento.getId())
                .nombre(evento.getNombre())
                .activo(evento.getEsActivo())
                .provincia(evento.getProvincia().getNombre())
                .build();

    }


}