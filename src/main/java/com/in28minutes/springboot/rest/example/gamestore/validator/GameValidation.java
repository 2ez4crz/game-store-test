package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.GameRequest;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.repository.GameRepository;
import com.in28minutes.springboot.rest.example.gamestore.util.DateUtil;
@Component
public class GameValidation {
	@Autowired
	GameRepository gameRepository;
	
	public void gameAgeRestrictionValidation(Date birthdate, int ageRestriction) {
		int age = DateUtil.getAge(birthdate);
		if(age<ageRestriction) {
			throw new ForbiddenException("This game is only for "+ageRestriction+" years old or older.", ExceptionEnum.AGE_RESTRICTED);
		}
	}
	public void createGameValidator(GameRequest input) {
		List<Long> genre = input.getGenre();
		if(genre.isEmpty()) {
			throw new BadRequestException("Genre cannont be empty.", ExceptionEnum.INVALID_INPUT);
		}
		if(input.getPrice()<1) {
			throw new BadRequestException("Price should be at least Rp 1,00",ExceptionEnum.INVALID_INPUT);
		}
	}
	public void unpublishGameValidator(Game game, User user) {
		if(game.getPublisher() != user.getPublisher()) {
			throw new ForbiddenException("You have no right to unpublish this game.", ExceptionEnum.ACCESS_DENIED);
		}
		if(!game.isPublished()) {
			throw new BadRequestException("Game is already unpublished", ExceptionEnum.GAME_IS_NOT_PUBLISHED);
		}
	}
}
