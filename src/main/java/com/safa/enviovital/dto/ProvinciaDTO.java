package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Provincia;
import lombok.*;

@Data
@Builder
public class ProvinciaDTO {
    private Integer id;
    private String nombre;


    public static ProvinciaDTO createProvinciaDTOFromProvincia(Provincia provincia) {
        return ProvinciaDTO.builder()
                .id(provincia.getId())
                .nombre(provincia.getNombre())
                .build();
    }
}
