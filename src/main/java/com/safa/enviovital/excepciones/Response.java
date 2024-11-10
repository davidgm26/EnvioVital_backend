package com.safa.enviovital.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private String mensaje;
    private int statusCode;
    private LocalDateTime timestamp;
}
