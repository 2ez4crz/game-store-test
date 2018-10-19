package com.in28minutes.springboot.rest.example.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends GeneralRequestException { 
	public BadRequestException(String exception) {
		super(exception);
	}

	public BadRequestException(ExceptionEnum enumeratedCause) {
		super(enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, ExceptionEnum enumeratedCause) {
		super(message, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, Throwable cause, ExceptionEnum enumeratedCause) {
		super(message, cause, enumeratedCause);
		// TODO Auto-generated constructor stub
	}
	
}
