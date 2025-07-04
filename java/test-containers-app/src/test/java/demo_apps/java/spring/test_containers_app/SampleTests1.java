package demo_apps.java.spring.test_containers_app;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import demo_apps.java.spring.test_containers_app.dao.CustomerRepository;
import demo_apps.java.spring.test_containers_app.entities.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * E2E tests for the CustomerController using Testcontainers with PostgreSQL. See CustomerControllerTests2.java for
 * better alternative approach using @Container annotation.
 */
// Use random port for the web environment to avoid conflicts.  This is used to make REST call in tests.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleTests1 {

	@LocalServerPort
	private Integer port;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@Autowired
	CustomerRepository customerRepository;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		customerRepository.deleteAll();
	}

	@Test
	void shouldGetAllCustomers() {
		List<Customer> customers = List.of(new Customer(null, "John", "john@mail.com"),
				new Customer(null, "Dennis", "dennis@mail.com"));
		customerRepository.saveAll(customers);

		given().contentType(ContentType.JSON).when().get("/api/customers").then().statusCode(200).body(".", hasSize(2));
	}
}
