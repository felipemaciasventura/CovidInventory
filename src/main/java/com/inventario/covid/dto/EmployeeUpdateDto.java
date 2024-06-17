package com.inventario.covid.dto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

public record EmployeeUpdateDto(
        Date birthDate,
        String address,
        String cellPhone,
        Boolean isRegComplete,
        Boolean isVaccineApplied
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
