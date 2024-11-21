package com.safa.enviovital.excepciones;

public class PasswordNotCorrectException extends RuntimeException {

  public PasswordNotCorrectException() {
    super("La contraseña introducida no es correcta");
  }

}
