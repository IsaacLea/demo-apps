package demo_apps.java.spring.bankservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class BankserviceApplication {

	@Bean
	UserDetailsService userDetailsService() {

		// Some users for demo purposes only
		UserDetails isaac = User.withDefaultPasswordEncoder().username("isaac").password("password").roles("USER")
				.build();

		UserDetails josh = User.withDefaultPasswordEncoder().username("josh").password("password").roles("USER")
				.build();

		UserDetails accountant = User.withDefaultPasswordEncoder().username("accountant").password("password")
				.roles("ACCOUNTANT").build();

		return new InMemoryUserDetailsManager(isaac, josh, accountant);
	}

	public static void main(String[] args) {
		SpringApplication.run(BankserviceApplication.class, args);
	}

}
