package com.safa.enviovital.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    private String username,password;
}
