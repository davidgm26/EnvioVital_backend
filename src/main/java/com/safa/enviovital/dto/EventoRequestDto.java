package com.safa.enviovital.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventoRequestDto {

private String nombre;
private String descripcion;
private int idProvincia;




}
