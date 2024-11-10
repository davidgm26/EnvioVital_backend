package com.safa.enviovital.excepciones.NotFoundException;

public class EventoNotFoundException extends RuntimeException {

    public EventoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
