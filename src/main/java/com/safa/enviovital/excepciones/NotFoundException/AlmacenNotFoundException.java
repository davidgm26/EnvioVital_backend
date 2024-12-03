package com.safa.enviovital.excepciones.NotFoundException;

public class AlmacenNotFoundException extends RuntimeException {

    public AlmacenNotFoundException(int id) {
        super("El almacén con ID " + id + " no existe");
    }

    public AlmacenNotFoundException() {
        super("No existen almacenes");
    }

    public AlmacenNotFoundException(String username) {
        super("El almacén con nombre de usuario " + username + " no existe");}
}
