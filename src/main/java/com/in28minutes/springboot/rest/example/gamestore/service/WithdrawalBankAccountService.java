package com.in28minutes.springboot.rest.example.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.entity.WithdrawalBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.repository.WithdrawalBankAccountRepository;

@Service
public class WithdrawalBankAccountService {
	@Autowired
	WithdrawalBankAccountRepository withdrawalBankRepository;
	
	public WithdrawalBankAccount doWithdraw(WithdrawalBankAccount input) {
		return withdrawalBankRepository.save(input);
	}
}
