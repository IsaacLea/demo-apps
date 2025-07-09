package demo_apps.java.spring.bankservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import demo_apps.java.spring.bankservice.dto.AccountDTO;
import demo_apps.java.spring.bankservice.service.BankAccountServiceImpl;

@RestController
public class AccountController {

	@Autowired
	private BankAccountServiceImpl accountService;

	@GetMapping("/accounts/{accountId}")
	public AccountDTO getAccount(@PathVariable long accountId) {
		System.out.println("Fetching account details for account ID: " + accountId);
		return accountService.getAccountDetails(accountId);
	}
}
