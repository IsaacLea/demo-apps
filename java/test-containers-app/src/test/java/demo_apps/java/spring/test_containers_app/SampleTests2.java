package demo_apps.java.spring.test_containers_app;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import demo_apps.java.spring.test_containers_app.dao.CustomerRepository;
import demo_apps.java.spring.test_containers_app.entities.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/*
 * Example of using Testcontainers with Spring Boot to test a REST controller.
 * This does not make use of the @ServiceConnection annotation, but instead uses the @Container annotation to manage the lifecycle of the PostgreSQL container.
 */
// Use random port for the web environment to avoid conflicts.  This is used to make REST call in tests.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//Enable Testcontainers lifecycle management so it will start and stop the @Container automatically
@Testcontainers
class SampleTests2 {

	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

//	@Container
//	static MSSQLServerContainer<?> sqlServer = new PostgreSQLContainer<>("postgres:16-alpine");

	@LocalServerPort
	private Integer port;

	// Note, it is possible to configure spring to use the compose.yaml properties here and avoid the need to manually
	// register these props
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
