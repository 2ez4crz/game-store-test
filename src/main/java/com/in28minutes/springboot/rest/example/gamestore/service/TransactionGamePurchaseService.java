package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionGamePurchase;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.TransactionGamePurchaseRepository;

@Service
public class TransactionGamePurchaseService {
	@Autowired
	TransactionGamePurchaseRepository gamePurchaseRepository;
	
	public TransactionGamePurchase doGamePurchase(TransactionGamePurchase input) {
		return gamePurchaseRepository.save(input);
	}
	
	public Long getNextId() {
		Long id = gamePurchaseRepository.getHighestData();
		return id + 1;
	}
	public TransactionGamePurchase retrieveGamePurchaseByTransaction(Transaction transaction) {
		Optional<TransactionGamePurchase> gamePurchased = gamePurchaseRepository.findByTransaction(transaction);
		if(!gamePurchased.isPresent()) {
			throw new ClassNotFoundException("Transaction with invoice : "+transaction.getInvoice()+" doesn't exist",ExceptionEnum.CLASS_NOT_FOUND);
		}
		return gamePurchased.get();
	}
}
