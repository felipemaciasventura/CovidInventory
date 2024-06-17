package com.inventario.covid.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeVaccineDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer dosesApplied;
    private LocalDate appliedVaccineDate;

    public EmployeeVaccineDto(int dosesApplied, LocalDate appliedVaccineDate) {
        this.dosesApplied = dosesApplied;
        this.appliedVaccineDate = appliedVaccineDate;
    }

    public EmployeeVaccineDto() {
    }

    @Override
    public String toString() {
        return "EmployeeVaccineDto{" +
                "appliedVaccineDate=" + appliedVaccineDate +
                ", dosesApplied=" + dosesApplied +
                '}';
    }
}
