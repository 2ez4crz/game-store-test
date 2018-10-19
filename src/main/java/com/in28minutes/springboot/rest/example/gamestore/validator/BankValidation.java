package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.Bank;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.repository.BankRepository;

@Component
public class BankValidation {
	@Autowired
	BankRepository bankRepository;
	public void isBankNameAlreadyExistValidator(String name) {
		Optional<Bank> bank = bankRepository.findByBankName(name);
		if(bank.isPresent()) {
			throw new BadRequestException("Bank with name "+name+ " already exist.");
		}
	}
}
