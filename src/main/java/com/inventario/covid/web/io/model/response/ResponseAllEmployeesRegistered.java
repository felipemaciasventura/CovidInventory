package com.inventario.covid.web.io.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAllEmployeesRegistered {

    private Long id;
    private Integer dni;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cellPhone;
    private Boolean isRegComplete;
    private Boolean isVaccineApplied;

}
