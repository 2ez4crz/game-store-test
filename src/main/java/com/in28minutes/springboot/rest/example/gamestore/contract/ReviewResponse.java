package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.List;

public class ReviewResponse {
	private List<GameReviewResponse> gameReview;
	private float gameScore;
	private Long totalReview;
	public ReviewResponse() {
		
	}
	
	public ReviewResponse(List<GameReviewResponse> gameReview, float gameScore, Long totalReview) {
		
		this.gameReview = gameReview;
		this.gameScore = gameScore;
		this.totalReview = totalReview;
	}

	public List<GameReviewResponse> getGameReview() {
		return gameReview;
	}

	public void setGameReview(List<GameReviewResponse> gameReview) {
		this.gameReview = gameReview;
	}

	public float getGameScore() {
		return gameScore;
	}
	public void setGameScore(float gameScore) {
		this.gameScore = gameScore;
	}

	public Long getTotalReview() {
		return totalReview;
	}

	public void setTotalReview(Long totalReview) {
		this.totalReview = totalReview;
	}
	
	
}
