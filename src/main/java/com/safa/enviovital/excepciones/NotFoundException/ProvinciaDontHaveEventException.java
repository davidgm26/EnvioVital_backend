package com.safa.enviovital.excepciones.NotFoundException;

public class ProvinciaDontHaveEventException extends RuntimeException {

    public ProvinciaDontHaveEventException(String nombreProvincia) {
        super(nombreProvincia + " no tiene eventos asociados");
    }
}
