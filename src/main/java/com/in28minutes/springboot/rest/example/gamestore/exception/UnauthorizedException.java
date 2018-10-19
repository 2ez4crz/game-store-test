package com.in28minutes.springboot.rest.example.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends GameStoreException {

	public UnauthorizedException(ExceptionEnum enumeratedCause) {
		super(enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedException(String message, ExceptionEnum enumeratedCause) {
		super(message, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedException(String message, Throwable cause, ExceptionEnum enumeratedCause) {
		super(message, cause, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
