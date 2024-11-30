package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Conductor;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConductorResponseDTO {

    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String email;
    private Integer idUsuario;
    private boolean activo;

    public static ConductorResponseDTO ConductorResponseDtoFromConductor(Conductor conductor) {
        return ConductorResponseDTO.builder()
                .id(conductor.getId())
                .nombre(conductor.getNombre())
                .apellidos(conductor.getApellidos())
                .dni(conductor.getDni())
                .direccion(conductor.getDireccion())
                .telefono(conductor.getTelefono())
                .fechaNacimiento(conductor.getFechaNacimiento())
                .email(conductor.getEmail())
                .idUsuario(conductor.getUsuario().getId())
                .activo(conductor.getEsActivo())
                .build();
    }


}
