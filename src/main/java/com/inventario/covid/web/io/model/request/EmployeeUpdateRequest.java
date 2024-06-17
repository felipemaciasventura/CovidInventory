package com.inventario.covid.web.io.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EmployeeUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "The first name field is required")
    @Size(min = 3, max = 70, message = "The first name field must have a suitable length")
    @Pattern(regexp="^[a-zA-Z\\s]*$",message = "The first name field must only contain letters")
    private String firstName;

    @NotEmpty(message = "The last name field is required")
    @Size(min = 3, max = 70, message = "The last name field must have a suitable length")
    @Pattern(regexp="^[a-zA-Z\\s]*$",message = "The last name field must only contain letters")
    private String lastName;

    @NotEmpty(message = "The email field is required")
    @Email(message = "The email field must contain a valid address", flags={Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @NotEmpty(message = "The address field is required")
    @Pattern(regexp="^[a-zA-Z\\s]*$",message = "The address field must only contain letters")
    private String address;

    @NotEmpty(message = "The cell phone field is required")
    @Pattern(regexp = "^\\d{8}$", message = "The cell field must contain eight digits")
    private String cellPhone;

}
