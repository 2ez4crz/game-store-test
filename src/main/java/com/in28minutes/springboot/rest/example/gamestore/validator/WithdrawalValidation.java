package com.in28minutes.springboot.rest.example.gamestore.validator;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalRequest;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.PublisherBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.entity.Withdrawal;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.InsufficientBalanceException;
import com.in28minutes.springboot.rest.example.gamestore.exception.TransactionRejectedException;

@Component
public class WithdrawalValidation {
	public void withdrawalBalanceValidation(Withdrawal input, Publisher publisher) {
		if(input.getAmount()<1) {
			throw new TransactionRejectedException("Withdrawal amount must be at least Rp. 1,00!", ExceptionEnum.INVALID_TRANSACTION_AMOUNT);
		}
		if(input.getAmount()>publisher.getSellingBalance()) {
			throw new InsufficientBalanceException(ExceptionEnum.INSUFFICIENT_BALANCE);
		}
	}
	public void withdrawalToBankAccountValidation(WithdrawalRequest input, Publisher publisher,PublisherBankAccount bankAccount) {
		if(input.getAmount()<50000) {
			throw new TransactionRejectedException("Withdrawal amount must be at least Rp. 50.000,00!", ExceptionEnum.INVALID_TRANSACTION_AMOUNT);
		}
		if(input.getAmount()>publisher.getSellingBalance()) {
			throw new InsufficientBalanceException(ExceptionEnum.INSUFFICIENT_BALANCE);
		}
		if(publisher.getBankAccount().size()==0) {
			throw new TransactionRejectedException("You have to have at least 1 bank account.", ExceptionEnum.BANK_ACCOUNT_NEEDED);
		}
		if(bankAccount.getPublisher()!=publisher) {
			throw new TransactionRejectedException("Bank Account Doesn't match",ExceptionEnum.BANK_ACCOUNT_DOES_NOT_MATCH);
		}
		if(!bankAccount.isActive()) {
			throw new TransactionRejectedException("Bank Account is not active.",ExceptionEnum.BANK_ACCOUNT_DOES_NOT_ACTIVE);
		}
	}
}
