package demo_apps.java.spring.test_containers_app;

import org.springframework.boot.SpringApplication;

public class TestTestContainersAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestContainersAppApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
