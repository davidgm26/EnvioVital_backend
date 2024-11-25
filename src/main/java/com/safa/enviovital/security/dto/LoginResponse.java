package com.safa.enviovital.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;
    private String username;
    private String rol;

}
