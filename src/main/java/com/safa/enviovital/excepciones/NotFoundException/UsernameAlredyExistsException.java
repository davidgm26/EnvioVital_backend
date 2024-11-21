package com.safa.enviovital.excepciones.NotFoundException;

import javax.security.sasl.AuthenticationException;

public class UsernameAlredyExistsException extends AuthenticationException {
    public UsernameAlredyExistsException(String username) {
        super("El nombre de usuario: " + username + " ya existe.");
    }

}

