package com.safa.enviovital.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoRequestDTO {
    private String marca;
    private String modelo;
    private String matricula;
    private Integer idConductor;
    private Integer idTipoVehiculo;
}
