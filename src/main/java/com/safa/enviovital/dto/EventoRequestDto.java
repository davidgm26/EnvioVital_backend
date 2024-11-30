package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.Provincia;
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

    public static Evento EventoFromRequest (EventoRequestDto eventodto, Evento e, Provincia provincia) {
        e.setNombre(eventodto.getNombre());
        e.setDescripcion(eventodto.getDescripcion());
        e.setProvincia(provincia);
        return e;
    }








}
