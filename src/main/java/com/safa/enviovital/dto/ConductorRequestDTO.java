package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Conductor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConductorRequestDTO {

    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String email;
    private UsuarioRequestDTO usuario;

    public static Conductor conductorFromRequest(ConductorRequestDTO dto) {
        return Conductor.builder()
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .dni(dto.getDni())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .fechaNacimiento(dto.getFechaNacimiento())
                .build();

    }

}
