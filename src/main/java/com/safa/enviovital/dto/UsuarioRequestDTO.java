package com.safa.enviovital.dto;

import com.safa.enviovital.enumerados.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    private String username;
    private String password;
}
