package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.Date;

import com.in28minutes.springboot.rest.example.gamestore.entity.GameReview;

public class GameReviewResponse {
private Long id;
	
	private String username;
	
	private String name;

	private Date date;
	
	private int score;
	
	private String review;
	
	public GameReviewResponse() {
		
	}
	
	public GameReviewResponse(GameReview gameReview) {
		this.id = gameReview.getId();
		this.username = gameReview.getUser().getUsername();
		this.name = gameReview.getUser().getName();
		this.date = gameReview.getDate();
		this.score = gameReview.getScore();
		this.review = gameReview.getReview();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
}
