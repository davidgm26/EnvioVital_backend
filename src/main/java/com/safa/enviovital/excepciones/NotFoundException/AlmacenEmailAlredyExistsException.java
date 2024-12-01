package com.safa.enviovital.excepciones.NotFoundException;

public class AlmacenEmailAlredyExistsException extends RuntimeException {
    public AlmacenEmailAlredyExistsException(String email) {
        super("El email: " + email + " ya se encuentra registrado");
    }
}

