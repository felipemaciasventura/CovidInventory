package com.inventario.covid.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "employee", schema = "public")
@Entity
public class EmployeeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "dni", nullable = false, columnDefinition = "varchar(10)", unique = true)
    private String dni;

    @Column(name = "username", nullable = false,  columnDefinition = "varchar(40)", unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false,  columnDefinition = "varchar(70)")
    private String firstName;

    @Column(name = "last_name", nullable = false,  columnDefinition = "varchar(70)")
    private String lastName;

    @Column(name="email", nullable = false,  columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "registration_complete",nullable = false, columnDefinition = "boolean default false")
    private Boolean isRegComplete = false;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name="address", columnDefinition = "varchar(50)")
    private String address;

    @Column(name = "cell_phone", columnDefinition = "varchar(8)")
    private String cellPhone;

    @Column(name = "vaccine_application", nullable = false, columnDefinition = "boolean default false")
    private Boolean isVaccineApplied = false;

}
