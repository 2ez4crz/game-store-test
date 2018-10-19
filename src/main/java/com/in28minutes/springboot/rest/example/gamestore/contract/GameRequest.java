package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class GameRequest {
	@NotBlank
	private String gameName;
	@NotBlank
	private String gameDescription;
	@NotNull
	private float price;
	@NotBlank
	private String ratingCode;
	@NotNull
    private List<Long> genre;
	
    public GameRequest() {
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getRatingCode() {
		return ratingCode;
	}

	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}

	public List<Long> getGenre() {
		return genre;
	}

	public void setGenre(List<Long> genre) {
		this.genre = genre;
	}

	
    
}
