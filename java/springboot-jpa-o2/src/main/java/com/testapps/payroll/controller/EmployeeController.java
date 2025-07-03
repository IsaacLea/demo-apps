package com.testapps.payroll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testapps.payroll.dtos.EmployeeDto;
import com.testapps.payroll.exceptions.EmployeeNotFoundException;
import com.testapps.payroll.mappers.EmployeeMapper;
import com.testapps.payroll.model.Employee;
import com.testapps.payroll.repository.EmployeeRepository;

@RestController
class EmployeeController {

	private final EmployeeRepository repository;
	private final EmployeeMapper employeeMapper;

	EmployeeController(EmployeeRepository repository, EmployeeMapper employeeMapper) {
		this.repository = repository;
		this.employeeMapper = employeeMapper;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/employees")
	List<Employee> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	// Single item
	@GetMapping("/employees/{id}")
	EmployeeDto one(@PathVariable Long id) {

		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

		return employeeMapper.toEmployeeDto(employee);

	}

	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return repository.findById(id).map(employee -> {
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			return repository.save(employee);
		}).orElseGet(() -> {
			return repository.save(newEmployee);
		});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}