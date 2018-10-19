package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SignupRequest {
	@NotNull(message="name musn't be null")
	private String name;
	@NotNull(message="username musn't be null")
	private String username;
	@NotNull(message="password musn't be null")
	private String password;
	@NotNull(message="email musn't be null")
	private String email;
	@NotNull(message="bithdate musn't be null")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
