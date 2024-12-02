package com.safa.enviovital.excepciones.NotFoundException;

public class AlmacenUsuarioAlredyExistsException extends RuntimeException {
    public AlmacenUsuarioAlredyExistsException(String username) {
        super("El usuario con el nombre de usuario: " + username + " ya está registrado");
    }
}