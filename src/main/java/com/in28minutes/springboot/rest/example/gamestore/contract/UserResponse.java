package com.in28minutes.springboot.rest.example.gamestore.contract;

import com.in28minutes.springboot.rest.example.gamestore.entity.User;

public class UserResponse {
	private Long id;

	private String name;
	
	private String username;

	public UserResponse() {
		
	}
	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	
}
