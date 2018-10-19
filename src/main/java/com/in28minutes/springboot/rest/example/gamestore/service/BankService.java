package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.BankRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Bank;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.repository.BankRepository;
import com.in28minutes.springboot.rest.example.gamestore.validator.BankValidation;

@Service
public class BankService {
	@Autowired
	BankRepository bankRepository;
	@Autowired
	BankValidation bankValidation;
	
	@LogActivity(activity="Add Bank")
	public Bank addBank( UserPrincipal currentUser, BankRequest input) {
		Bank bank = new Bank(input);
		bankValidation.isBankNameAlreadyExistValidator(bank.getBankName());
		bank.setStatus(true);
		Bank savedBank = bankRepository.save(bank);
		return savedBank;
	}
	public Bank retrieveBank(int id) {
		Optional<Bank> bank = bankRepository.findById(id);
		if(!bank.isPresent()) {
			throw new ClassNotFoundException("Bank Not Found.");
		}
		return bank.get();
	}
	public List<Bank> retrieveAllActiveBanks(){
		return bankRepository.findAllByStatus(true);
	}
}
