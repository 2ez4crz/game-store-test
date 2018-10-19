package com.in28minutes.springboot.rest.example.gamestore.exception;


public class GameStoreException extends RuntimeException {
	private ExceptionEnum enumeratedCause;
	public GameStoreException ( String message){
		super(message);
	}
	public GameStoreException ( ExceptionEnum enumeratedCause){
		super(enumeratedCause.getMessage());
		this.enumeratedCause = enumeratedCause;
	}
	
	public GameStoreException ( String message, ExceptionEnum enumeratedCause){
		super(message);
		this.enumeratedCause = enumeratedCause;
	}
	
	public GameStoreException ( String message, Throwable cause, ExceptionEnum enumeratedCause){
		super(message,cause);
		this.enumeratedCause = enumeratedCause;
	}

	public ExceptionEnum getEnumeratedCause() {
		return enumeratedCause;
	}
	
}
