package com.inventario.covid.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false, columnDefinition = "varchar(13)", unique = true)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeRoleEntity> employeeRoles = new HashSet<>();

}
