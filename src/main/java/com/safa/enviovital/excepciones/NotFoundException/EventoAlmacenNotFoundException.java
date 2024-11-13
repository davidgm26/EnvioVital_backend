package com.safa.enviovital.excepciones.NotFoundException;

public class EventoAlmacenNotFoundException extends RuntimeException {

    public EventoAlmacenNotFoundException(String mensaje) {
        super(mensaje);
    }
}
