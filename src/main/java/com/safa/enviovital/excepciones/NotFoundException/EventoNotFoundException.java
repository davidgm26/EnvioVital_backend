package com.safa.enviovital.excepciones.NotFoundException;

public class EventoNotFoundException extends RuntimeException {

    public EventoNotFoundException(int id) {
        super("El evento con el id " + id + " no existe");
    }

    public EventoNotFoundException() {
        super("No se han encontrado eventos");
    }
}
