package demo_apps.java.spring.bankservice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

/**
 * Custom annotation to mock a user named "isaac" for testing purposes.
 */
@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "isaac", roles = {"USER"})
public @interface WithMockIsaac {

}