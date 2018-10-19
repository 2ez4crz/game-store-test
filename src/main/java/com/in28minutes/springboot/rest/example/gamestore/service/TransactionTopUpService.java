package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionTopUp;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.TransactionTopUpRepository;

@Service
public class TransactionTopUpService {
	@Autowired
	TransactionTopUpRepository topUpRepository;
	@Autowired
	TransactionService transactionService;
	
	public TransactionTopUp doTopUp(TransactionTopUp topUp) {
		TransactionTopUp savedTopUp = topUpRepository.save(topUp);
		return savedTopUp;
	}
	public Long  getNextId() {
		Long id = topUpRepository.getHighestData();
		return id+1;
	}
	public TransactionTopUp retrieveTopUpByTransaction(Transaction transaction) {
		Optional<TransactionTopUp> topUp = topUpRepository.findByTransaction(transaction);
		if(!topUp.isPresent()) {
			throw new ClassNotFoundException("Transaction with invoice : " + transaction.getInvoice()+" doesn't exist", ExceptionEnum.CLASS_NOT_FOUND);
		}
		return topUp.get();
	}
}
