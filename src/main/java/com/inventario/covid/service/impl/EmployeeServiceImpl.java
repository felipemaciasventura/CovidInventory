package com.inventario.covid.service.impl;


import com.inventario.covid.config.ErrorMessages;
import com.inventario.covid.config.Utils;
import com.inventario.covid.dto.EmployeeDto;
import com.inventario.covid.dto.EmployeeVaccineDto;
import com.inventario.covid.exceptions.UserServiceException;
import com.inventario.covid.mapper.EmployeeMapper;
import com.inventario.covid.model.EmployeeEntity;
import com.inventario.covid.model.EmployeeRoleEntity;
import com.inventario.covid.model.RoleEntity;
import com.inventario.covid.repository.EmployeeRepository;
import com.inventario.covid.repository.RoleRepository;
import com.inventario.covid.service.IEmployeeService;
import com.inventario.covid.web.io.model.response.ResponseAllEmployeesRegistered;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final Utils utils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    private static final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Autowired
    public EmployeeServiceImpl(Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder, EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) throws UserServiceException {
        EmployeeEntity newUser = convertToEntity(employeeDto);
        EmployeeEntity storedUserDetails = employeeRepository.save(newUser);
        return convertToDto(storedUserDetails);
    }

    private EmployeeEntity convertToEntity(EmployeeDto employeeDto){
        EmployeeEntity newUser = employeeMapper.employeeDtoToEntity(employeeDto);
        newUser.setUserName(utils.generateUsername(employeeDto.getFirstName(), employeeDto.getLastName()));
        String passwordNoEncode = utils.generatePassword(10);
        logger.info("passwordNoEncode" + passwordNoEncode);
        newUser.setPassword(bCryptPasswordEncoder.encode(passwordNoEncode));
        RoleEntity role = roleRepository.findByRoleName(employeeDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        EmployeeRoleEntity employeeRole = new EmployeeRoleEntity();
        employeeRole.setEmployee(newUser);
        employeeRole.setRole(role);
        newUser.getEmployeeRolesEntity().add(employeeRole);
        return newUser;
    }

    private EmployeeDto convertToDto(EmployeeEntity employeeEntity){
        return employeeMapper.employeeEntityToDto(employeeEntity);
    }

    @Override
    public boolean checkIfUserExist(String dni, String email) {
        return employeeRepository.existsByDniOrEmail(dni, email);
    }

    @Override
    public List<EmployeeDto> getEmployees(int page, int limit) {

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<EmployeeEntity> employeesPage = employeeRepository.findAll(pageableRequest);
        return employeeMapper.employeeEntitiesToDtos(employeesPage.getContent());
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            logger.info("Service: Employee with ID {} deleted successfully.", id);
        } else {
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployeeForAdmin(Long id, EmployeeDto employeeDto) throws UserServiceException {
        EmployeeEntity employeeEntity = employeeRepository.findById(id);
        if(employeeEntity == null){
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND_EXCEPTION.getErrorMessage());
        }
        /*employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setAddress(employeeDto.getAddress());
        employeeEntity.setCellPhone(employeeDto.getCellPhone());*/
        employeeMapper.updateEmployeeFromDto(employeeDto, employeeEntity);

        EmployeeEntity updateEmployeeDetails = employeeRepository.save(employeeEntity);

        /*EmployeeDto returnValue = new EmployeeDto();
        BeanUtils.copyProperties(updateEmployeeDetails, returnValue);*/
        return employeeMapper.employeeEntityToDto(updateEmployeeDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseAllEmployeesRegistered getEmployeeByEmployeeId(Long id) throws UserServiceException {
        EmployeeEntity employeeEntity = employeeRepository.findById(id);
        if(employeeEntity == null) throw new UserServiceException(ErrorMessages.EMPLOYEE_ID_NOT_FOUND_EXCEPTION.getErrorMessage());

        ResponseAllEmployeesRegistered returnValue = new ResponseAllEmployeesRegistered();
        BeanUtils.copyProperties(employeeEntity, returnValue);
        return returnValue;
    }

    @Override
    public EmployeeDto updateEmployeeFromEmployee(Long idEmployee, EmployeeDto employeeDto) throws UserServiceException {

        EmployeeEntity employeeEntity = employeeRepository.findById(idEmployee);
        if (employeeEntity == null) {
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND_EXCEPTION.getErrorMessage());
        }
        employeeMapper.INSTANCE.updateEmployeeFromDto(employeeDto, employeeEntity);
        employeeEntity.setIsRegComplete(true);
        EmployeeEntity updateEmployeeDetails = employeeRepository.save(employeeEntity);
        return EmployeeMapper.INSTANCE.employeeEntityToDto(updateEmployeeDetails);
    }

    @Override
    public EmployeeDto updateEmployeeForEmployee(Long idEmployee, Long idVaccine, EmployeeDto employeeDto, EmployeeVaccineDto employeeVaccineDto) throws UserServiceException {
        return null;
    }

}
