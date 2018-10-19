package com.in28minutes.springboot.rest.example.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionRejectedException extends GameStoreException {

	public TransactionRejectedException(String msg) {
		super(msg);
	}
	public TransactionRejectedException( String message, ExceptionEnum enumeratedCause){
		super(message, enumeratedCause);
	}
	public TransactionRejectedException( ExceptionEnum enumeratedCause){
		super(enumeratedCause);
	}
}
