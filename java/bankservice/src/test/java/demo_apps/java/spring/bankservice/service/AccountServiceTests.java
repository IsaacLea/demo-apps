package demo_apps.java.spring.bankservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import demo_apps.java.spring.bankservice.WithMockAccountant;
import demo_apps.java.spring.bankservice.WithMockIsaac;
import demo_apps.java.spring.bankservice.dto.AccountDTO;

@SpringBootTest
public class AccountServiceTests {

	@Autowired
	private BankAccountServiceImpl accountService;
	// These aren't needed when using the @WithMockUser annotation
//	private void login(long accountId) {
//		Authentication auth = new TestingAuthenticationToken(accountId, "password", "ROLE_USER");
//		SecurityContextHolder.getContext().setAuthentication(auth);
//	}
//
//	@AfterEach
//	public void tearDown() {
//		SecurityContextHolder.clearContext();
//	}

	@Test
	@WithMockIsaac // Example of a custom annotation to mock a user named "isaac"
	public void getAccountWhenAccountOwner() {

//		AccountServiceImpl accountService = new AccountServiceImpl();
		AccountDTO accountDTO = accountService.getAccountDetails(1);
		assertEquals(10000, accountDTO.getBalance(), "The account balance should be 10000");

		accountService.getAccountDetails(1);
	}

	@Test
	@WithMockAccountant // Example of a custom annotation to mock a user with the role "ACCOUNTANT"
	public void getAccountWhenAccountant() {

//		AccountServiceImpl accountService = new AccountServiceImpl();
		AccountDTO accountDTO = accountService.getAccountDetails(1);
		assertEquals(10000, accountDTO.getBalance(), "The account balance should be 10000");

	}

	@Test
	@WithMockAccountant
	public void getAccountNumberWhenAccountant() {

		AccountDTO accountDTO = accountService.getAccountDetails(1);

		// The accountant isn't the account owner, so the account number should be masked
		assertThat(accountDTO.getAccountNumber()).isEqualTo("****");
	}

	@Test
	@WithMockIsaac
	public void getAccountNumberWhenOwner() {

		AccountDTO accountDTO = accountService.getAccountDetails(1);

		assertThat(accountDTO.getAccountNumber()).isEqualTo("11112222");
	}

	@Test
	@WithMockUser("baduser")
	public void getByIdWhenDenied() {

//		AccountServiceImpl accountService = new AccountServiceImpl();

		assertThrows(AccessDeniedException.class, () -> {
			accountService.getAccountDetails(2);
		}, "An AccessDeniedException should be thrown when access is denied");

	}

	@Test
	@WithMockIsaac
	public void saveAccountWhenOwner() {

		AccountDTO toSave = new AccountDTO(1, "isaac", "11112222", 10000);
		accountService.save(toSave); // Should not throw an exception

	}

	@Test
	@WithMockUser("baduser")
	public void saveAccountWhenDenied() {

		AccountDTO toSave = new AccountDTO(1, "isaac", "11112222", 10000);

		assertThrows(AuthorizationDeniedException.class, () -> {
			accountService.save(toSave);
		}, "An AccessDeniedException should be thrown when access is denied");

	}

}