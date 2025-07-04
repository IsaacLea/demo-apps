package demo_apps.java.spring.test_containers_app.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import demo_apps.java.spring.test_containers_app.dao.CustomerDAO;
import demo_apps.java.spring.test_containers_app.entities.Customer;

@RestController
public class CustomerController {

	private final CustomerDAO customerDAO;

	CustomerController(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@GetMapping("/api/customers")
	List<Customer> getAll() {
		return customerDAO.findAll();
	}

}