package demo_apps.java.spring.bankservice.dto;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;

import demo_apps.java.spring.bankservice.MaskAuthorizationDeniedHandler;

public class AccountDTO {

	private long id;
	private String owner;
	private String accountNumber;
	private double balance;

	public AccountDTO(long id, String owner, String accountNumber, double balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	// Example of using a field-level security annotation to restrict access
	@PreAuthorize("this.owner == authentication?.name")
	// Override the default behavior of throwing an access denied exception
	@HandleAuthorizationDenied(handlerClass = MaskAuthorizationDeniedHandler.class)
	public String getAccountNumber() {
		return accountNumber;
	}

}