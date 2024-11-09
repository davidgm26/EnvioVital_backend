package com.safa.enviovital.excepciones.NotFoundException;

public class AlmacenNotFoundException extends RuntimeException {

    public AlmacenNotFoundException(String mensaje) {
        super(mensaje);
    }
}
