package demo_apps.java.spring.test_containers_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
public class IntegrationTests {

	@Autowired
	private static PostgreSQLContainer<?> database;

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", database::getJdbcUrl);
		registry.add("spring.datasource.username", database::getUsername);
		registry.add("spring.datasource.password", database::getPassword);
	}

	@Test
	void testWithPostgresContainer() {

		database.start();

//		DockerImageName myImage = DockerImageName.parse("mcr.microsoft.com/mssql/server:2022-latest")
//				.asCompatibleSubstituteFor("mcr.microsoft.com/mssql/server");
//		MSSQLServerContainer database = new MSSQLServerContainer(myImage);
//		database.start();

		String jdbcUrl = database.getJdbcUrl();
		String username = database.getUsername();
		String password = database.getPassword();

		// Add logic to test database connection or queries using jdbcUrl, username, and password
		System.out.println("SQL Server container started with JDBC URL: " + jdbcUrl);

		database.stop();
	}
}