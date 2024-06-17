package com.inventario.covid.mapper;

import com.inventario.covid.dto.EmployeeDto;
import com.inventario.covid.model.EmployeeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T13:48:01-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.3 (N/A)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeEntity employeeDtoToEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setId( employeeDto.getId() );
        employeeEntity.setDni( employeeDto.getDni() );
        employeeEntity.setUserName( employeeDto.getUserName() );
        employeeEntity.setPassword( employeeDto.getPassword() );
        employeeEntity.setFirstName( employeeDto.getFirstName() );
        employeeEntity.setLastName( employeeDto.getLastName() );
        employeeEntity.setEmail( employeeDto.getEmail() );
        employeeEntity.setRole( employeeDto.getRole() );
        employeeEntity.setIsRegComplete( employeeDto.getIsRegComplete() );
        employeeEntity.setBirthDate( employeeDto.getBirthDate() );
        employeeEntity.setAddress( employeeDto.getAddress() );
        employeeEntity.setCellPhone( employeeDto.getCellPhone() );
        employeeEntity.setIsVaccineApplied( employeeDto.getIsVaccineApplied() );

        return employeeEntity;
    }

    @Override
    public EmployeeDto employeeEntityToDto(EmployeeEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employeeEntity.getId() );
        employeeDto.setDni( employeeEntity.getDni() );
        employeeDto.setUserName( employeeEntity.getUserName() );
        employeeDto.setPassword( employeeEntity.getPassword() );
        employeeDto.setFirstName( employeeEntity.getFirstName() );
        employeeDto.setLastName( employeeEntity.getLastName() );
        employeeDto.setEmail( employeeEntity.getEmail() );
        employeeDto.setRole( employeeEntity.getRole() );
        employeeDto.setBirthDate( employeeEntity.getBirthDate() );
        employeeDto.setAddress( employeeEntity.getAddress() );
        employeeDto.setCellPhone( employeeEntity.getCellPhone() );
        employeeDto.setIsRegComplete( employeeEntity.getIsRegComplete() );
        employeeDto.setIsVaccineApplied( employeeEntity.getIsVaccineApplied() );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> employeeEntitiesToDtos(List<EmployeeEntity> employeeEntities) {
        if ( employeeEntities == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employeeEntities.size() );
        for ( EmployeeEntity employeeEntity : employeeEntities ) {
            list.add( employeeEntityToDto( employeeEntity ) );
        }

        return list;
    }
}
