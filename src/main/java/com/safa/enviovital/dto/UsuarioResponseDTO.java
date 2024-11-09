package com.safa.enviovital.dto;

import com.safa.enviovital.enumerados.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Integer id;
    private String username;
    private Rol rol;
}
