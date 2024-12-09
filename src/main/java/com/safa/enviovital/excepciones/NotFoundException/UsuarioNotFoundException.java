package com.safa.enviovital.excepciones.NotFoundException;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}
