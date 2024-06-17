package com.inventario.covid.repository;

import com.inventario.covid.model.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
    EmployeeEntity findByDni(String dni);
    EmployeeEntity findById(Long id);
    EmployeeEntity save(EmployeeEntity newUser);
    EmployeeEntity deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByDniOrEmail(String ci, String email);
}
