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

import demo_apps.java.spring.test_containers_app.dao.CustomerDAO;
import demo_apps.java.spring.test_containers_app.entities.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTests {

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
	CustomerDAO customerDAO;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		customerDAO.deleteAll();
	}

	@Test
	void shouldGetAllCustomers() {
		List<Customer> customers = List.of(new Customer(null, "John", "john@mail.com"),
				new Customer(null, "Dennis", "dennis@mail.com"));
		customerDAO.saveAll(customers);

		given().contentType(ContentType.JSON).when().get("/api/customers").then().statusCode(200).body(".", hasSize(2));
	}
}
