package com.inventario.covid.exceptions;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String message){
        super(message);
    }

}
