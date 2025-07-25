package demo_apps.java.spring.test_containers_app;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
	}

//	@ServiceConnection
//	MSSQLServerContainer<?> sqlServerContainer() {
//		return new MSSQLServerContainer<>(DockerImageName.parse("mcr.microsoft.com/mssql/server:2022-latest"));
//	}

}
