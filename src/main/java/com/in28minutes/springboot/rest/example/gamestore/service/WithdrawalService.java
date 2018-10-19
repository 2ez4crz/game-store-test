package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalAmountRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalRequest;
import com.in28minutes.springboot.rest.example.gamestore.entity.Bank;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.PublisherBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.entity.Withdrawal;
import com.in28minutes.springboot.rest.example.gamestore.entity.WithdrawalBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.Status;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.WithdrawalType;
import com.in28minutes.springboot.rest.example.gamestore.repository.WithdrawalRepository;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.validator.WithdrawalValidation;

@Service
public class WithdrawalService {
	@Autowired
	WithdrawalRepository withdrawalRepository;
	@Autowired
	UserService userService;
	@Autowired
	PublisherBankAccountService  bankAccountService;
	@Autowired 
	WithdrawalBankAccountService withdrawalBankService;
	@Autowired
    PasswordEncoder passwordEncoder;
	@Autowired
	WithdrawalValidation withdrawalValidation;
	

	@LogActivity(activity="Withdraw to private balance")
	@Transactional
	public Withdrawal withdrawToPrivateBalance(UserPrincipal currentUser, WithdrawalAmountRequest input) {
		
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		Withdrawal withdrawal = new Withdrawal(user, publisher, input.getAmount(), WithdrawalType.Withdraw_To_Private_Balance, Status.SUCCESS); 
		withdrawalValidation.withdrawalBalanceValidation(withdrawal, publisher);
		float newPublisherBalance = publisher.getSellingBalance() - input.getAmount();
		float newUserBalance = user.getBalance() + input.getAmount();
		
		Withdrawal savedWithdrawal = withdrawalRepository.save(withdrawal);
		
		publisher.setSellingBalance(newPublisherBalance);
		user.setBalance(newUserBalance);
		
		return savedWithdrawal;
	}
	
	public List<Withdrawal> retrieveAllWithdrawal(){
		return withdrawalRepository.findAll();
	}
	public List<Withdrawal> retrieveWithdrawalHistory(UserPrincipal currentUser){
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		return withdrawalRepository.findAllByPublisher(publisher);
	}

	@LogActivity(activity="Withdraw to bank account")
	@Transactional
	public WithdrawalBankAccount withdrawToBankAccount( UserPrincipal currentUser, WithdrawalRequest input) {
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		PublisherBankAccount bankAccount = bankAccountService.retrieveBankAccount(input.getBankAccountId());
		withdrawalValidation.withdrawalToBankAccountValidation(input, publisher, bankAccount);
		Bank bank = bankAccount.getBank();
		float newPublisherBalance = publisher.getSellingBalance() - input.getAmount();
		Withdrawal withdrawal = new Withdrawal(user, publisher, input.getAmount(), WithdrawalType.Withdraw_To_Bank_Account, Status.SUCCESS);
		
		Withdrawal savedWithdrawal = withdrawalRepository.save(withdrawal);
		WithdrawalBankAccount withdrawalBankAccount = new WithdrawalBankAccount(savedWithdrawal, bank, bankAccount.getAccountNumber());
		WithdrawalBankAccount savedWIthdrawalBank = withdrawalBankService.doWithdraw(withdrawalBankAccount);
		publisher.setSellingBalance(newPublisherBalance);
		
		return savedWIthdrawalBank;
	}
}
