package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class GameReviewRequest {
	@NotNull(message="score cannot be null")
	private int score;
	@NotBlank(message="review cannot be null")
	private String review;
	public GameReviewRequest() {
		
	}
	public GameReviewRequest(int score, String review) {
		super();
		this.score = score;
		this.review = review;
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
