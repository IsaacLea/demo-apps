package demo_apps.java.spring.bankservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankserviceApplicationTests {

	@Value("${spring.application.name}")
	private String appName;

	@Test
	void contextLoads() {
		// This test will pass if the application context loads successfully
		// and the application name is set correctly.
		System.out.println("Application Name: " + appName);
		assert appName.equals("bankserviceTEST");
	}

}
