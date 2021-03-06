package com.in28minutes.springboot.rest.example.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends TransactionRejectedException {
	public InsufficientBalanceException(ExceptionEnum enumeratedCause) {
		super("Insufficient Balance", enumeratedCause);
	}
}
