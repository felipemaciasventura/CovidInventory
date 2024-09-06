package com.inventario.covid.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto extends EmployeeVaccineDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String dni;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private LocalDate birthDate = null;
    private String address = null;
    private String cellPhone = null;
    private Boolean isRegComplete = false;
    private Boolean isVaccineApplied = false;

    public EmployeeDto() {
        super();
    }

    public EmployeeDto(int dosesApplied, LocalDate appliedVaccineDate) {
        super(dosesApplied, appliedVaccineDate);
    }
}