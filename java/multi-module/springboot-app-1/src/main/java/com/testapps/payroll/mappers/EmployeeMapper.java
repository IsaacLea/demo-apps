package com.testapps.payroll.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.testapps.payroll.dtos.EmployeeDto;
import com.testapps.payroll.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	// Mapstruct will automatically generate the implementation for this method
	// based on the Employee and EmployeeDto classes.
	@Mapping(source = "name", target = "fullName")
	EmployeeDto toEmployeeDto(Employee employee);

	// Mapstruct will automatically generate the implementation for this method
	// based on the EmployeeDto and Employee classes.
	@Mapping(source = "fullName", target = "name")
	Employee toEmployee(EmployeeDto employeeDto);
}
