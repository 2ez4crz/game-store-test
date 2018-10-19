package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameReviewRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameReviewResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.ReviewResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameReview;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.repository.GameReviewRepository;
import com.in28minutes.springboot.rest.example.gamestore.validator.GameReviewValidation;

@Service
public class GameReviewService {
	@Autowired
	GameReviewRepository gameReviewRepository;
	@Autowired
	GameService gameService;
	@Autowired
	UserService userService;
	@Autowired
	GameReviewValidation gameReviewValidation;
	
	@LogActivity(activity="Review a Game")
	public GameReview addReview(UserPrincipal currentUser, Long gameId, GameReviewRequest input) {
		GameReview review = new GameReview(input);
		User user = userService.retrieveUser(currentUser.getId());
		Game game = gameService.retrieveGame(gameId);
//		Publisher publisher = publisherService.retrievePublisher(user.getPublisher().getId());
		gameReviewValidation.gameReviewValidator(review, game, user);
		review.setDate();
		review.setUser(user);
		review.setGame(game);
		
		GameReview savedReview = gameReviewRepository.save(review);
		
		return savedReview;
	}
	
	public List<GameReview> retrieveAllReviewByGame(Long gameId){
		Game game = gameService.retrieveGame(gameId);
		return gameReviewRepository.findAllByGame(game);
	}
	public ReviewResponse retieveReviewByGameAsResponse(Long gameId) {
		List<GameReview> reviewList = this.retrieveAllReviewByGame(gameId);
		float totalScore = (float) 0;
		Long totalReview = (long) 0;
		List<GameReviewResponse> reviews = new ArrayList<GameReviewResponse>();
		for(int i = 0; i<reviewList.size(); i++) {
			GameReviewResponse newReview = new GameReviewResponse(reviewList.get(i));
			reviews.add(newReview);
			totalScore = totalScore+reviewList.get(i).getScore();
			totalReview = totalReview+1;
		}
		float scoreAvg = totalScore/totalReview;
		ReviewResponse review = new ReviewResponse(reviews,scoreAvg, totalReview);
		return review;
	}

}
