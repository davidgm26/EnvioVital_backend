package com.safa.enviovital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenEditarDTO {
    private String nombre;
    private String descripcion;
    private String direccion;
    private String email;
    private Integer idProvincia;
}


