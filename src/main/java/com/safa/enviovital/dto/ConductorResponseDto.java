package com.safa.enviovital.dto;


import com.safa.enviovital.modelos.Conductor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConductorResponseDto {

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;

    public static ConductorResponseDto createConductorResponseDtoFromConductor(Conductor conductor) {}




}
