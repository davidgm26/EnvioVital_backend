package com.safa.enviovital.security.dto;

import com.safa.enviovital.modelos.Alerta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String token;
    private Integer id;
    private String username;
    private String rol;
    private List<Alerta> alertas;


}
