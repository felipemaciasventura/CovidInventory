package com.inventario.covid.exceptions;

public class NoRecordsFoundException extends RuntimeException{
    public NoRecordsFoundException(String message){
        super(message);
    }
}
