package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherBankAccountRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Bank;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.PublisherBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.repository.PublisherBankAccountRepository;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;

@Service
public class PublisherBankAccountService {
	@Autowired
	PublisherBankAccountRepository bankAccountRepository;
	@Autowired
	BankService bankService;
	@Autowired
	UserService userService;
	@LogActivity(activity="Add a bank account")
	public PublisherBankAccount addBankAccount( UserPrincipal currentUser, PublisherBankAccountRequest input) {
		if(!input.getAccountNumber().matches("[0-9]*")) {
			throw new BadRequestException("Bank Account Number can only filled by number.");
		}
		
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		Bank bank = bankService.retrieveBank(input.getBankId());
		
		
		PublisherBankAccount pba = new PublisherBankAccount(publisher, bank, input.getAccountNumber());
		
		PublisherBankAccount savedBankAccount = bankAccountRepository.save(pba);
		return savedBankAccount;
	}
	
	public PublisherBankAccount retrieveBankAccount(Long id) {
		Optional<PublisherBankAccount> bankAccount = bankAccountRepository.findById(id);
		if(!bankAccount.isPresent()) {
			throw new ClassNotFoundException("Bank Account Not Fount.");
		}
		return bankAccount.get();
	}
	public void deleteBankAccount(Long id) {
		PublisherBankAccount delete = this.retrieveBankAccount(id);
		bankAccountRepository.delete(delete);
	}
	public void setDeactive(Long id) {
		PublisherBankAccount bank = this.retrieveBankAccount(id);
		if(!bank.isActive()) {
			throw new BadRequestException("Bank Account deactivated already.");
		}
		bankAccountRepository.setBankAccountStatus(id, false);
	}
	public void setActive(Long id) {
		PublisherBankAccount bank = this.retrieveBankAccount(id);
		if(bank.isActive()) {
			throw new BadRequestException("Bank Account already active.");
		}
		bankAccountRepository.setBankAccountStatus(id, true);
	}
}
