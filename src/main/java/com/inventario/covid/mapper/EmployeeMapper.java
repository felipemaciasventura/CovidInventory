package com.inventario.covid.mapper;

import com.inventario.covid.dto.EmployeeDto;
import com.inventario.covid.model.EmployeeEntity;
import com.inventario.covid.web.io.model.request.EmployeeRegisterRequest;
import com.inventario.covid.web.io.model.request.EmployeeUpdateRequest;
import com.inventario.covid.web.io.model.response.EmployeeRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeEntity employeeDtoToEntity(EmployeeDto employeeDto);
    EmployeeDto employeeEntityToDto(EmployeeEntity employeeEntity);
    List<EmployeeDto> employeeEntitiesToDtos(List<EmployeeEntity> employeeEntities);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "dni" , ignore = true)
    @Mapping(target = "userName" , ignore = true)
    @Mapping(target = "password" , ignore = true)
    @Mapping(target = "isRegComplete", ignore = true)
    @Mapping(target = "isVaccineApplied", ignore = true)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget EmployeeEntity entity);

    EmployeeDto updateRequestToDto(EmployeeUpdateRequest employeeUpdateRequest);
    EmployeeRegisterResponse dtoToRegisterResponse(EmployeeDto employeeDto);


}
