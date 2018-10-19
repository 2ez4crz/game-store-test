package com.in28minutes.springboot.rest.example.gamestore.exception;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String errorCode;
	private String detail;
	private String message;
	private String path;
	public Date getTimestamp() {
		return timestamp;
	}
	public ErrorDetails(Date timestamp, String errorCode, String detail, String message, String path) {
		this.timestamp = timestamp;
		this.errorCode = errorCode;
		this.detail = detail;
		this.message = message;
		this.path = path;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
