package demo_apps.java.spring.bankservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
// Enable method level security annotations, for example @PreAuthorize, @PostAuthorize, etc.
@EnableMethodSecurity
public class SecurityConfig {
	// Existing or additional security configurations can go here
}