package com.safa.enviovital.dto;


import com.safa.enviovital.modelos.Conductor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConductorResponseDto {

    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;

    public static ConductorResponseDto createConductorResponseDtoFromConductor(Conductor conductor) {
        return ConductorResponseDto.builder()
                .nombre(conductor.getNombre())
                .apellidos(conductor.getApellidos())
                .dni(conductor.getDni())
                .telefono(conductor.getTelefono())
                .build();
    }




}
