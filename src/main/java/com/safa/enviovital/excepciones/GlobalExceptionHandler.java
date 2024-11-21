package com.safa.enviovital.excepciones;

import com.safa.enviovital.excepciones.NotFoundException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TipoVehiculoNotFoundException.class)
    public ResponseEntity<Response> handleTipoVehiculoNotFoundException(TipoVehiculoNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Response> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProvinciaNotFoundException.class)
    public ResponseEntity<Response> handleProvinciaNotFoundException(ProvinciaNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProvinciaDontHaveEventException.class)
    public ResponseEntity<Response> handleProvinciaDontHaveEventException(ProvinciaDontHaveEventException ex){
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehiculoNotFoundException.class)
    public ResponseEntity<Response> handleVehiculoNotFoundException(VehiculoNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoNotFoundException.class)
    public ResponseEntity<Response> handleEventoNotFoundException(EventoNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConductorNotFoundException.class)
    public ResponseEntity<Response> handleConductorNotFoundException(ConductorNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlmacenNotFoundException.class)
    public ResponseEntity<Response> handleAlmacenNotFoundException(AlmacenNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
        Response errorResponse = new Response(
                "Ocurrió un error inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameAlredyExistsException.class)
    public ResponseEntity<Response>handleUsernameAlredyExistsException(UsernameAlredyExistsException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response>handleUsernameNotFoundException(UsernameNotFoundException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    public ResponseEntity<Response>handlePasswordNotCorrectException(PasswordNotCorrectException ex) {
        Response errorResponse = new Response(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

}
