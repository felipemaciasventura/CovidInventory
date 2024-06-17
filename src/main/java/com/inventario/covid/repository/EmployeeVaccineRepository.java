package com.inventario.covid.repository;

import com.inventario.covid.model.EmployeeVaccineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeVaccineRepository extends CrudRepository<EmployeeVaccineEntity, Long> {
}
