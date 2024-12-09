package com.safa.enviovital.excepciones.NotFoundException;



public class TipoVehiculoNotFoundException extends RuntimeException {

    public TipoVehiculoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
