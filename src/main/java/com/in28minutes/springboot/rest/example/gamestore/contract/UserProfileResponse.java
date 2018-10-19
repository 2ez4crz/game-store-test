package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.Date;
import java.util.Set;

import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;

public class UserProfileResponse {
	private Long id;
	
	private String name;
	
	private String username;
	
	private String email;

	private String photoProfile;
	
	private Date birthDate;
	
	private Date registeredDate;
	
	private float balance;
	
	private Publisher publisher;
	
    private Set<GameResponse> gameOwned;

    public UserProfileResponse(){
    	
    }
    public UserProfileResponse(User user, Publisher publisher, Set<GameResponse> gameOwned) {
    	this.id = user.getId();
    	this.name = user.getName();
    	this.username = user.getUsername();
    	this.email = user.getEmail();
    	this.photoProfile = user.getPhotoProfile();
    	this.birthDate = user.getBirthDate();
    	this.registeredDate = user.getRegisteredDate();
    	this.balance = user.getBalance();
    	this.publisher=publisher;
    	this.gameOwned = gameOwned;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoProfile() {
		return photoProfile;
	}
	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<GameResponse> getGameOwned() {
		return gameOwned;
	}

	public void setGameOwned(Set<GameResponse> gameOwned) {
		this.gameOwned = gameOwned;
	}
    
    
}
