package demo_apps.java.spring.bankservice.service;

import org.springframework.security.access.prepost.PreAuthorize;

import demo_apps.java.spring.bankservice.annotations.PostReadBankAccount;
import demo_apps.java.spring.bankservice.dto.AccountDTO;

public interface BankAccountService {

	// Example of using a custom annotation to encapsulate the auth logic
	@PostReadBankAccount
	AccountDTO getAccountDetails(long accountId);

	@PreAuthorize("#toSave?.owner == authentication?.name")
	default void save(AccountDTO toSave) {
		// Does nothing
	}

	@PreAuthorize("#toUpdate?.owner == authentication?.name")
	default void update(AccountDTO toUpdate) {
		// Does nothing
	}

}
