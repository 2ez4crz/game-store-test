package com.in28minutes.springboot.rest.example.gamestore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.InsufficientBalanceException;
import com.in28minutes.springboot.rest.example.gamestore.exception.TransactionRejectedException;
@Component
public class TransactionValidation {
	@Autowired
	GameValidation gameValidation;
	
	public void balanceValidation(float price, float balance) {
		if(price>balance)
			throw new InsufficientBalanceException(ExceptionEnum.INSUFFICIENT_BALANCE);
	}
	public void gamePurchaseValidation(Game game, User user) {
		gameValidation.gameAgeRestrictionValidation(user.getBirthDate(), game.getRatingCode().getAgeRestriction());
		if(!game.isPublished()) {
			throw new TransactionRejectedException("Game is not published anymore.", ExceptionEnum.GAME_IS_NOT_PUBLISHED);
		}
		if(user.getPublisher() == game.getPublisher()) {
			throw new TransactionRejectedException("Cannot buy your own game.", ExceptionEnum.YOU_ARE_THE_PUBLISHER);
		}
		if(user.getGameOwned().contains(game)) {
			throw new TransactionRejectedException("Game ALready Purchased.", ExceptionEnum.GAME_ALREADY_PURCHASED);
		}
		this.balanceValidation(game.getPrice(), user.getBalance());
	}
}
