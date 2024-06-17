package com.inventario.covid.config;

import lombok.Getter;

@Getter
public enum SuccessMessages {

    RECORD_DELETED_SUCCESSFULLY("Record deleted successfully"),
    USER_DELETED_SUCCESSFULLY("Successfully registered user"),
    RECORD_MODIFIED_SUCCESSFULLY("Successfully modified record");

    private final String successMessages;
    SuccessMessages(String successMessages) {
        this.successMessages = successMessages;
    }
}
