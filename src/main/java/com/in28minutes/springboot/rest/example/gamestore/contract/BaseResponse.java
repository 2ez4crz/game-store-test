package com.in28minutes.springboot.rest.example.gamestore.contract;

public class BaseResponse {
	private Object data;
	private String message;
	
	public BaseResponse() {
	}
	public BaseResponse(Object data, String message) {
		this.data = data;
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
