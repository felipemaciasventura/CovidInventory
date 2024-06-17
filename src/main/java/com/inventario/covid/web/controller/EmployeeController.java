package com.inventario.covid.web.controller;

import com.inventario.covid.config.ErrorMessages;
import com.inventario.covid.config.SuccessMessages;
import com.inventario.covid.dto.EmployeeDto;
import com.inventario.covid.exceptions.UserServiceException;
import com.inventario.covid.service.IEmployeeService;
import com.inventario.covid.web.io.model.request.EmployeeRegisterRequest;
import com.inventario.covid.web.io.model.request.EmployeeUpdateRequest;
import com.inventario.covid.web.io.model.response.EmployeeRegisterResponse;
import com.inventario.covid.web.io.model.response.ResponseAllEmployeesRegistered;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final IEmployeeService employeeService;
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeRegisterRequest employeeRegisterRequest, BindingResult result){

        Map<String, Object> responseCreateEmployee = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            responseCreateEmployee.put("errors", errors);
            logger.error("Error register employee: {}", errors);
            return ResponseEntity.badRequest().body(responseCreateEmployee);
        }
        try{
            if (employeeService.checkIfUserExist(employeeRegisterRequest.getDni(), employeeRegisterRequest.getEmail())){
                logger.debug("ERROR! An attempt was made to write a record that already exists");
                responseCreateEmployee.put("message", ErrorMessages.RECORD_ALREADY_EXISTS_EXCEPTION.getErrorMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseCreateEmployee);
            }
            EmployeeDto createdUser = employeeService.save(employeeRegisterRequest.toDto());
            logger.info("Registering employee: {}", createdUser.getUserName());

            EmployeeRegisterResponse returnValue = new EmployeeRegisterResponse();
            BeanUtils.copyProperties(createdUser, returnValue);
            responseCreateEmployee.put("message", "Registered employee successfully");
            responseCreateEmployee.put("employee", returnValue);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseCreateEmployee);

        }
        catch(DataAccessException ex){
            responseCreateEmployee.put("message", ErrorMessages.ERROR_EMPLOYEE_REGISTER.getErrorMessage());
            responseCreateEmployee.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
            logger.error("Exception during employee registration: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCreateEmployee);
        }
    }
    
    @GetMapping("/employees")
    public ResponseEntity<Object> indexEmployees(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                               @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<EmployeeDto> employees = employeeService.getEmployees(page, limit);

        if (employees.isEmpty()){
            Map<String, Object> emptyResponse = new HashMap<>();
            emptyResponse.put("message", ErrorMessages.EMPTY_RECORDS.getErrorMessage());
            return ResponseEntity.ok(emptyResponse);
        }

        List<ResponseAllEmployeesRegistered> employeeResponses = employees.stream()
                .map(employeeDto -> {
                    ResponseAllEmployeesRegistered registerResponse = new ResponseAllEmployeesRegistered();
                    BeanUtils.copyProperties(employeeDto, registerResponse);
                    return registerResponse;
                })
                .toList();

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", employeeResponses);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployeeRegistered(@PathVariable Long id){
        Map<String, Object> responseDeleteEmployee = new HashMap<>();
        try{
            employeeService.deleteEmployee(id);
            responseDeleteEmployee.put("message", SuccessMessages.RECORD_DELETED_SUCCESSFULLY.getSuccessMessages());
            return ResponseEntity.ok(responseDeleteEmployee);
        } catch (EntityNotFoundException ex){
            responseDeleteEmployee.put("error", ErrorMessages.RECORD_NOT_FOUND_EXCEPTION.getErrorMessage());
            logger.info("Error deleting employee record: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDeleteEmployee);
        }
        catch(Exception ex){
            responseDeleteEmployee.put("message", ErrorMessages.GENERIC_ERROR_MESSAGE_DELETE_RECORD.getErrorMessage());
            logger.error("Error deleting employee record: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDeleteEmployee);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            ResponseAllEmployeesRegistered employeeDto = employeeService.getEmployeeByEmployeeId(id);
            response.put("message", employeeDto);
        } catch (UserServiceException ex) {
            response.put("error", ErrorMessages.EMPLOYEE_ID_NOT_FOUND_EXCEPTION.getErrorMessage());
            logger.info("Error getting employee record with id {}: {}", id, ex.getMessage(), ex);
            status = HttpStatus.NOT_FOUND;
        } catch (Exception ex) {
            response.put("error", ErrorMessages.LOGGER_GET_RECORDS_NOT_FOUND.getErrorMessage());
            logger.error("Error getting employee record with id {}: {}", id, ex.getMessage(), ex);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployeeForAdmin(@Valid @RequestBody EmployeeUpdateRequest newEmployee, BindingResult result, @PathVariable Long id){
        EmployeeRegisterResponse returnValue = new EmployeeRegisterResponse();
        logger.debug("Updating employee with id: {}", id);
        Map<String, Object> responseUpdateEmployee = new HashMap<>();
        var employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(newEmployee, employeeDto);

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            responseUpdateEmployee.put("errors", errors);
            logger.error("Error updating employee: {}", errors);
            return ResponseEntity.badRequest().body(responseUpdateEmployee);
        }
        try {
            EmployeeDto updateEmployee = employeeService.updateEmployeeForAdmin(id, employeeDto);
            BeanUtils.copyProperties(updateEmployee, returnValue);
        } catch (DataAccessException exception){
            responseUpdateEmployee.put("message", ErrorMessages.GENERIC_ERROR_LOGGER_SERVICE.getErrorMessage());
            responseUpdateEmployee.put("error", exception.getMessage() + ": " + exception.getMostSpecificCause().getMessage());
            logger.error("Exception during employee update: {}", exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUpdateEmployee);
        } catch (UserServiceException exception){
            responseUpdateEmployee.put("message", exception.getMessage());
            logger.error("Exception during employee update: {}", exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseUpdateEmployee);
        }
        responseUpdateEmployee.put("message", "Employee updated successfully");
        responseUpdateEmployee.put("employee", returnValue);
        return new ResponseEntity<>(responseUpdateEmployee, HttpStatus.CREATED);
    }
}
