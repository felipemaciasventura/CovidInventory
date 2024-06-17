package com.inventario.covid.mapper;

import com.inventario.covid.dto.EmployeeDto;
import com.inventario.covid.model.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeEntity employeeDtoToEntity(EmployeeDto employeeDto);
    EmployeeDto employeeEntityToDto(EmployeeEntity employeeEntity);
    List<EmployeeDto> employeeEntitiesToDtos(List<EmployeeEntity> employeeEntities);

}
