package com.safa.enviovital.excepciones.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

public class ProvinciaNotFoundException extends EntityNotFoundException {

    public ProvinciaNotFoundException(int id) {
        super("No se ha encontrado la provincia con id " + id);
    }
    public ProvinciaNotFoundException() {
        super("No se han encontrado provincias");
    }
}
