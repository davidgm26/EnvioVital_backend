package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.modelos.Conductor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AlmacenResponseDto {

    private String nombre;
    private String descripcion;
    private String provincia;
    private boolean estado;
    private List<ConductorResponseDto> conductores;

    public static AlmacenResponseDto createAlmacenResponseDtoFromAlmacen(Almacen almacen) {
        return AlmacenResponseDto.builder()
                .nombre(almacen.getNombre())
                .descripcion(almacen.getDescripcion())
                .estado(almacen.getEsActivo())
                .conductores(almacen.getConductores().stream().map(ConductorResponseDto::createConductorResponseDtoFromConductor).collect(Collectors.toList()))
                .provincia(almacen.getProvincia().getNombre())
                .build();
    }





}
