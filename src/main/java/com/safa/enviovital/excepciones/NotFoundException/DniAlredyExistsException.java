package com.safa.enviovital.excepciones.NotFoundException;

import javax.security.sasl.AuthenticationException;

public class DniAlredyExistsException extends AuthenticationException {
    public DniAlredyExistsException(String dni) {
        super("El DNI: " + dni + " ya existe.");
    }
}