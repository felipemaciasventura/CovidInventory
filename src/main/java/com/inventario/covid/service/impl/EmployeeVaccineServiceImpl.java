package com.inventario.covid.service.impl;

import com.inventario.covid.model.EmployeeVaccineEntity;
import com.inventario.covid.repository.EmployeeVaccineRepository;
import com.inventario.covid.service.IEmployeeVaccineService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeVaccineServiceImpl implements IEmployeeVaccineService {

    private final EmployeeVaccineRepository employeeVaccineRepository;

    public EmployeeVaccineServiceImpl(EmployeeVaccineRepository employeeVaccineRepository) {
        this.employeeVaccineRepository = employeeVaccineRepository;
    }

    @Override
    public EmployeeVaccineEntity save(EmployeeVaccineEntity employeeVaccineEntity) {
        EmployeeVaccineEntity newEmployeeVaccineEntity;
        newEmployeeVaccineEntity = employeeVaccineRepository.save(employeeVaccineEntity);
        return newEmployeeVaccineEntity;
    }
}
