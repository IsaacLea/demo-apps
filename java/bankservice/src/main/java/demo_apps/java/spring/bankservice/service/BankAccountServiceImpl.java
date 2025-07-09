package demo_apps.java.spring.bankservice.service;

import org.springframework.stereotype.Service;

import demo_apps.java.spring.bankservice.dto.AccountDTO;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	public AccountDTO getAccountDetails(long accountId) {
		return new AccountDTO(1, "isaac", "11112222", 10000);
	}

}
