package com.inventario.covid.config;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    RECORD_NOT_FOUND_EXCEPTION("The record could not be found or does not exist"),
    RECORD_ALREADY_EXISTS_EXCEPTION("There is already a registered account with one of the data provided!"),
    ERROR_EMPLOYEE_REGISTER("Error entering employee record"),
    EMPLOYEE_ID_NOT_FOUND_EXCEPTION("Employee record could not be found or does not exist"),
    EMPTY_RECORDS("Records not found"),
    GENERIC_ERROR_MESSAGE_DELETE_RECORD("A problem occurred while trying to delete the registry"),
    GENERIC_ERROR_LOGGER_SERVICE("A problem occurred on the service"),
    /*Messages for loggers*/
    LOGGER_GET_RECORDS_FOUND("Request database records"),
    LOGGER_GET_RECORDS_NOT_FOUND("Request database records not found");

    private final String errorMessage;
    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
