package com.testapps.payroll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testapps.payroll.model.Employee;

@RestController
class SampleController {

	
	SampleController() {		
	}

	@GetMapping("/sample")
	List<Employee> all() {
		return null;
	}
	
}