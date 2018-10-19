package com.in28minutes.springboot.rest.example.gamestore.exception;

public class GeneralRequestException extends GameStoreException {
	public GeneralRequestException ( String msg){
		super(msg);
	}
	
	public GeneralRequestException ( ExceptionEnum enumeratedCause){
		super(enumeratedCause);
	}
	
	public GeneralRequestException ( String message, ExceptionEnum enumeratedCause){
		super(message, enumeratedCause);
	}
	
	public GeneralRequestException ( String message, Throwable cause, ExceptionEnum enumeratedCause){
		super(message,cause, enumeratedCause);
	}
}
