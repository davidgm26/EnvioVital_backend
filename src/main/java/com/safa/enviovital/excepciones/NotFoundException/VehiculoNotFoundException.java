package com.safa.enviovital.excepciones.NotFoundException;

public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
