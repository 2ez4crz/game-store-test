package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EditUserProfileRequest {
	private String name;
	private String email;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	public EditUserProfileRequest() {
		
	}
	public EditUserProfileRequest(String name, String email, Date birthDate) {
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
