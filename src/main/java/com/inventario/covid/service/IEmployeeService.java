package com.inventario.covid.service;

import com.inventario.covid.dto.EmployeeDto;
import com.inventario.covid.dto.EmployeeVaccineDto;
import com.inventario.covid.exceptions.UserServiceException;
import com.inventario.covid.web.io.model.response.ResponseAllEmployeesRegistered;

import java.util.List;

public interface IEmployeeService {

    EmployeeDto save(EmployeeDto employeeDto) throws UserServiceException;
    boolean checkIfUserExist(String dni, String email);
    List<EmployeeDto> getEmployees(int page, int limit);
    void deleteEmployee(Long id);
    EmployeeDto updateEmployeeForAdmin(Long id, EmployeeDto employeeDto) throws UserServiceException;
    ResponseAllEmployeesRegistered getEmployeeByEmployeeId(Long id)throws UserServiceException;
    EmployeeDto updateEmployeeForEmployee(Long idEmployee, Long idVaccine, EmployeeDto employeeDto, EmployeeVaccineDto employeeVaccineDto) throws UserServiceException;
    EmployeeDto updateEmployeeFromEmployee(Long idEmployee, EmployeeDto employeeDto) throws UserServiceException;
}
