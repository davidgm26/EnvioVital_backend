package com.safa.enviovital.excepciones.NotFoundException;

public class ConductorNotFoundException extends RuntimeException {

    public ConductorNotFoundException(int id) {
        super("El conductor con el id " + id + " no existe");
    }

    public ConductorNotFoundException(String mensaje) {
        super("No se han encontrado conductores");
    }
}
