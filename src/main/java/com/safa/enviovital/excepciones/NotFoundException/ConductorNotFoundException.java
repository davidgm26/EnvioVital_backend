package com.safa.enviovital.excepciones.NotFoundException;

public class ConductorNotFoundException extends RuntimeException {

    public ConductorNotFoundException(String mensaje) {
        super(mensaje);
    }
}
