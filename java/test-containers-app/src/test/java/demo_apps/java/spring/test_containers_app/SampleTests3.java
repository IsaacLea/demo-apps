package demo_apps.java.spring.test_containers_app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import demo_apps.java.spring.test_containers_app.dao.CustomerDAO;
import demo_apps.java.spring.test_containers_app.entities.Customer;

/*
 * Example of using Testcontainers with Spring Boot to test a DAO layer.
 * This imports the TestcontainersConfiguration class which sets up a PostgreSQL container automatically because it is annotated with @ServiceConnection.
 * This is the ideal way to use Testcontainers with Spring Boot, as it allows the container to be managed by the Spring context.  
 * Not all resources support the @ServiceConnection annotation though, so you may need to use the @Container annotation for those resources.
 */
@Import(TestcontainersConfiguration.class)

// Use @DataJpaTest instead of @SpringBootTest to focus on JPA components only.  It loads only the JPA-related beans and does not start the entire application context.
@DataJpaTest
class SampleTests3 {

	@Autowired
	CustomerDAO customerDAO;

	@BeforeEach
	void setUp() {
		customerDAO.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void shouldGetAllCustomers() {
		List<Customer> customers = List.of(new Customer(null, "John", "john@mail.com"),
				new Customer(null, "Dennis", "dennis@mail.com"));

		customerDAO.saveAll(customers);

		assertEquals(2, customerDAO.findAll().size());

	}

}
