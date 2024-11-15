package com.safa.enviovital.dto;
import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.modelos.EventoAlmacen;
import com.safa.enviovital.modelos.EventoAlmacenConductor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListaAlmacenesRegistradosByConductorDTO {
    private Integer idAlmacen;
    private String nombreAlmacen;
    private String direccionAlmacen;
    private String nombreEvento;
    private String descripcionEvento;
    private String nombreProvincia;

    public static ListaAlmacenesRegistradosByConductorDTO toDto(EventoAlmacenConductor eventoAlmacenConductor) {
        return ListaAlmacenesRegistradosByConductorDTO.builder()
                .idAlmacen(eventoAlmacenConductor.getEventoAlmacen().getAlmacen().getId())
                .nombreAlmacen(eventoAlmacenConductor.getEventoAlmacen().getAlmacen().getNombre())
                .direccionAlmacen(eventoAlmacenConductor.getEventoAlmacen().getAlmacen().getDireccion())
                .nombreEvento(eventoAlmacenConductor.getEventoAlmacen().getEvento().getNombre())
                .descripcionEvento(eventoAlmacenConductor.getEventoAlmacen().getEvento().getDescripcion())
                .nombreProvincia(eventoAlmacenConductor.getEventoAlmacen().getEvento().getProvincia().getNombre())
                .build();
    }
}
