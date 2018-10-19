package com.in28minutes.springboot.rest.example.gamestore.contract;

public class MessageWithIdResponse {
	private String id;
	private String message;
	public MessageWithIdResponse() {
		
	}
	public MessageWithIdResponse(String id, String message) {
		this.id = id;
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
