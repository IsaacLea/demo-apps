package demo_apps.java.spring.bankservice.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;

/**
 * Custom annotation to encapsulate the authorization logic for reading bank account details.
 */
@Retention(RetentionPolicy.RUNTIME)
@PostAuthorize("returnObject?.owner == authentication?.name or hasRole('ACCOUNTANT')")
@AuthorizeReturnObject // Enables field level security for the return object
public @interface PostReadBankAccount {

}