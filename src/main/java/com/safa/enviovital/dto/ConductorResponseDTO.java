package com.safa.enviovital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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



}
