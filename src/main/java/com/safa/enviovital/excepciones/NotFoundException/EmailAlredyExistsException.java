package com.safa.enviovital.excepciones.NotFoundException;

import javax.security.sasl.AuthenticationException;

public class EmailAlredyExistsException extends AuthenticationException {
    public EmailAlredyExistsException(String email) {
        super("El correo electrónico: " + email + " ya existe.");
    }
}