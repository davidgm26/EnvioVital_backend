package com.safa.enviovital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoAlmacenDtoResponse {
    private Integer idEvento;
    private Integer idAlmacen;
    private String nombreAlmacen;
    private String nombreEvento;
}
