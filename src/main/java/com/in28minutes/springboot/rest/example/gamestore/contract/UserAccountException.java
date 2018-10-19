package com.in28minutes.springboot.rest.example.gamestore.contract;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.GameStoreException;


public class UserAccountException extends GameStoreException {

	public UserAccountException(ExceptionEnum enumeratedCause) {
		super(enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(String message, ExceptionEnum enumeratedCause) {
		super(message, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(String message, Throwable cause, ExceptionEnum enumeratedCause) {
		super(message, cause, enumeratedCause);
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
