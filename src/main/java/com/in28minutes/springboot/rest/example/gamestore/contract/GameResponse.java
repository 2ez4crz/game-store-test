package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.Genre;
import com.in28minutes.springboot.rest.example.gamestore.entity.Rating;

public class GameResponse {
	private Long id;
	
	private String gameName;
	
	private String gameDescription;
	
	private String publisherName;
	
	private Date publishedDate;
	
	private float price;
	
	private Rating rating;
	
    private Set<Genre> genre = new HashSet<>();
	
	private Long purchased;
	
	public GameResponse() {
		
	}
	public GameResponse(Game game) {
		this.id = game.getId();
		this.gameName = game.getGameName();
		this.gameDescription = game.getGameDescription();
		this.publisherName = game.getPublisher().getPublisherName();
		this.publishedDate = game.getPublishedDate();
		this.price = game.getPrice();
		this.rating = game.getRatingCode();
		this.genre = game.getGenre();
		this.purchased = game.getPurchased();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public Set<Genre> getGenre() {
		return genre;
	}
	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}
	public Long getPurchased() {
		return purchased;
	}
	public void setPurchased(Long purchased) {
		this.purchased = purchased;
	}
	
}
