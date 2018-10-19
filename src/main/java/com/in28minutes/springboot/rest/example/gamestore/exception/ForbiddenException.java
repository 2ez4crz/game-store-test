package com.in28minutes.springboot.rest.example.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.in28minutes.springboot.rest.example.gamestore.contract.UserAccountException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends UserAccountException  {
	public ForbiddenException(String exception) {
		super(exception);
	}

	public ForbiddenException(ExceptionEnum enumeratedCause) {
		super(enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message, ExceptionEnum enumeratedCause) {
		super(message, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message, Throwable cause, ExceptionEnum enumeratedCause) {
		super(message, cause, enumeratedCause);
		// TODO Auto-generated constructor stub
	}
	
}
