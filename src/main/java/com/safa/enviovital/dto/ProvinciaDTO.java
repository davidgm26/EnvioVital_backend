package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Provincia;
import lombok.*;

@Data
@Builder
public class ProvinciaDTO {
    private String nombre;


    public static ProvinciaDTO createProvinciaDTOFromProvincia(Provincia provincia) {
        return ProvinciaDTO.builder()
                .nombre(provincia.getNombre())
                .build();
    }
}
