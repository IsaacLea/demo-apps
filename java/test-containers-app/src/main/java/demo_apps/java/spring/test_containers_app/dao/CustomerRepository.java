package demo_apps.java.spring.test_containers_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo_apps.java.spring.test_containers_app.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
