package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameReview;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.DoubleTransactionException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.exception.PaymentRequiredException;
import com.in28minutes.springboot.rest.example.gamestore.repository.GameReviewRepository;
@Component
public class GameReviewValidation {
	@Autowired
	GameReviewRepository gameReviewRepository;
	public void isGameAlreadyReviewed(User user, Game game){
		Optional<GameReview> gameReview = gameReviewRepository.findByUserAndGame(user, game);
		if(gameReview.isPresent()) {
			throw new DoubleTransactionException("You have reviewed this game already.", ExceptionEnum.DOUBLE_TRANSACTION_DETECTED);
		}
	}
	public void gameReviewValidator(GameReview review, Game game, User user) {
		if(game.getPublisher()==user.getPublisher()) {
			throw new ForbiddenException("You're not allowed to review your own game.", ExceptionEnum.YOU_ARE_THE_PUBLISHER);
		}
		if(!user.getGameOwned().contains(game)) {
			throw new PaymentRequiredException("You have to purchase this game to review it", ExceptionEnum.PAYMENT_REQUIRED);
		}
		this.isGameAlreadyReviewed(user, game);
		if(review.getScore()>5 || review.getScore()<1) {
			throw new BadRequestException("Score can be only filled with number 1-5.", ExceptionEnum.INVALID_INPUT);
		}
	}
}
