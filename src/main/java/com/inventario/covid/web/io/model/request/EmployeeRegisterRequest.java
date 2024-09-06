package com.inventario.covid.web.io.model.request;

import com.inventario.covid.dto.EmployeeDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EmployeeRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "The dni field is required")
    //@Pattern(regexp = "^\\d{10}$", message = "The dni field must contain ten digits")
    @Pattern(regexp = "^\\d{10}$", message = "The dni field must contain ten digits")
    @Pattern(regexp = "^[0-9]+$", message = "Only numeric digits are allowed.")
    private String dni;

    @NotEmpty(message = "The first name field is required")
    @Size(min = 3, max = 70, message = "The name field must have a suitable length")
    @Pattern(regexp="^[a-zA-Z\\s]*$",message = "The first name field must only contain letters")
    private String firstName;

    @NotEmpty(message = "The last name field is required")
    @Size(min = 3, max = 70, message = "The last name field must have a suitable length")
    @Pattern(regexp="^[a-zA-Z\\s]*$",message = "The last name field must only contain letters")
    private String lastName;

    @NotEmpty(message = "The email field is required")
    @Email(message = "The email field must contain a valid address", flags={Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @NotEmpty(message = "The role field is required")
    private String roleName;

    public EmployeeDto toDto(){
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(this, employeeDto);
        return employeeDto;
    }

}
