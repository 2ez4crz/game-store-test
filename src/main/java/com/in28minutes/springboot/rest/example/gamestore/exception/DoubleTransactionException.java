package com.in28minutes.springboot.rest.example.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoubleTransactionException extends GameStoreException {

	public DoubleTransactionException(String msg) {
		super(msg);
	}

	public DoubleTransactionException(ExceptionEnum enumeratedCause) {
		super(enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public DoubleTransactionException(String message, ExceptionEnum enumeratedCause) {
		super(message, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public DoubleTransactionException(String message, Throwable cause, ExceptionEnum enumeratedCause) {
		super(message, cause, enumeratedCause);
		// TODO Auto-generated constructor stub
	}
	
}
