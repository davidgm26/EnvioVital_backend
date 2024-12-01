package com.safa.enviovital.excepciones.NotFoundException;

import javax.naming.AuthenticationException;

public class AlmacenNameAlredyExistsException extends AuthenticationException {
    public AlmacenNameAlredyExistsException(String nombre) {
        super("El almacen con el nombre: " + nombre + " ya se encuentra registrado");
    }
}
