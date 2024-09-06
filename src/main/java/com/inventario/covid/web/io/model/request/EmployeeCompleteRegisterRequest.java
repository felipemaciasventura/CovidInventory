package com.inventario.covid.web.io.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class EmployeeCompleteRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Past(message = "Check the date entered ")
    @NotNull(message = "The date of birth field is required")
    private Date birthDate;

    @NotEmpty(message = "The address field is required")
    private String address;

    @NotEmpty(message = "The cell phone field is required")
    @Pattern(regexp = "^\\d{8}$", message = "The cell phone field must contain eight digits")
    private String cellPhone;

    @NotNull(message = "The vaccine application field is mandatory")
    private Boolean isVaccineApplied;

    private Integer dosesApplied;
    private Date appliedVaccineDate;

}
