package com.safa.enviovital.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoVehiculoResponseDTO {
    private Integer id;
    private String nombre;



    public static TipoVehiculoResponseDTO TipoVehiculoResponseDtoFromTipoVehiculo(TipoVehiculoResponseDTO tipoVehiculo) {
        return TipoVehiculoResponseDTO.builder()
                .id(tipoVehiculo.getId())
                .nombre(tipoVehiculo.getNombre())
                .build();
    }

}
