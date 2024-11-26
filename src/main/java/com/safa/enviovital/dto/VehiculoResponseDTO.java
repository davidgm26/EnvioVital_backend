package com.safa.enviovital.dto;

import com.safa.enviovital.modelos.Vehiculo;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoResponseDTO {
    private Integer id;
    private String marca;
    private String modelo;
    private String matricula;
    private Integer idConductor;
    private Integer idTipoVehiculo;


    public static VehiculoResponseDTO VehiculoResponseDTOfromVehiculo(Vehiculo vehiculo) {
        return VehiculoResponseDTO.builder()
                .id(vehiculo.getId())
                .marca(vehiculo.getMarca())
                .modelo(vehiculo.getModelo())
                .matricula(vehiculo.getMatricula())
                .idConductor(vehiculo.getConductor().getId())
                .idTipoVehiculo(vehiculo.getTipoVehiculo().getId())
                .build();
    }
}
