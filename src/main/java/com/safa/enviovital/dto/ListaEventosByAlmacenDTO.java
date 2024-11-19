package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.EventoAlmacen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListaEventosByAlmacenDTO {

    private Integer id;
    private Integer idEvento;
    private String nombreEvento;
    private String descripcionEvento;
    private String nombreProvincia;
    private Boolean estado;


    public static ListaEventosByAlmacenDTO toDto(EventoAlmacen evento) {
        return ListaEventosByAlmacenDTO.builder()
                .id(evento.getId())
                .idEvento(evento.getEvento().getId())
                .nombreEvento(evento.getEvento().getNombre())
                .descripcionEvento(evento.getEvento().getDescripcion())
                .nombreProvincia(evento.getEvento().getProvincia().getNombre())
                .estado(evento.getEvento().getEsActivo())
                .build();
    }
}

