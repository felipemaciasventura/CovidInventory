package com.inventario.covid.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        String errorMessage = "Acceso denegado: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }*/
}
