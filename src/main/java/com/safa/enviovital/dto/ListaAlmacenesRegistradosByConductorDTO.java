package com.safa.enviovital.dto;
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
    private Integer id;
    private Integer idAlmacen;
    private String nombreAlmacen;
    private String direccionAlmacen;
    private String nombreEvento;
    private String descripcionEvento;
    private String nombreProvincia;
    private Boolean estado;
    public static ListaAlmacenesRegistradosByConductorDTO toDto(EventoAlmacenConductor eventoAlmacenConductor) {
        return ListaAlmacenesRegistradosByConductorDTO.builder()
                .id(eventoAlmacenConductor.getId())
                .idAlmacen(eventoAlmacenConductor.getEventoAlmacen().getAlmacen().getId())
                .nombreAlmacen(eventoAlmacenConductor.getEventoAlmacen().getAlmacen().getNombre())
                .direccionAlmacen(eventoAlmacenConductor.getEventoAlmacen().getAlmacen().getDireccion())
                .nombreEvento(eventoAlmacenConductor.getEventoAlmacen().getEvento().getNombre())
                .descripcionEvento(eventoAlmacenConductor.getEventoAlmacen().getEvento().getDescripcion())
                .nombreProvincia(eventoAlmacenConductor.getEventoAlmacen().getEvento().getProvincia().getNombre())
                .estado(eventoAlmacenConductor.getEventoAlmacen().getEvento().getEsActivo())

                .build();
    }
}
