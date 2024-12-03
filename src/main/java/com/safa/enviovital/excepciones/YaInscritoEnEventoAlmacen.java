package com.safa.enviovital.excepciones;

public class YaInscritoEnEventoAlmacen extends RuntimeException {
    public YaInscritoEnEventoAlmacen(String conductor) {
        super("El conductor " + conductor + " ya está inscrito a este almacen");
    }
}
