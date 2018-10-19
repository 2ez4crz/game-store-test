package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameDetailResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.ReviewResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.Genre;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.Rating;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.mapper.GameMapper;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.GameRepository;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.validator.GameValidation;
@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private PublisherService publisherService;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameReviewService reviewService;
	@Autowired
	private GameValidation gameValidation;
	@Autowired
	private GenreService genreService;

	@LogActivity(activity="Add New Game")
	@Transactional
	public Game createGame(@CurrentUser UserPrincipal currentUser, GameRequest input) {
		List<Long> genreCode = input.getGenre();
		gameValidation.createGameValidator(input);
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = publisherService.retrievePublisher(user.getPublisher().getId());
		Set<Genre> genres = new HashSet<Genre>();
		for(int i=0;i<genreCode.size();i++) {
			Genre genre = genreService.retrieveGenre(genreCode.get(i));
			genres.add(genre);
		}
		Rating rating = ratingService.getRating(input.getRatingCode());
		Game game = new Game(input, publisher, rating, genres);
		Game savedGame = gameRepository.save(game);
		return savedGame;
	}
	public Game retrieveGame(Long id) {
		Optional<Game> game = gameRepository.findById(id);
		if(!game.isPresent())
				throw new ClassNotFoundException("Game with id : "+id+" doesn't exist", ExceptionEnum.CLASS_NOT_FOUND);
		return game.get();
	}

	public void setPurchased(Game game) {
		gameRepository.setPurchased(game.getId(), game.getPurchased());
	}
	
	public List<GameResponse> retrieveGames() {
		List<Game> game = gameRepository.findAll();
		List<GameResponse> listGame = GameMapper.ListGameToListGameResponse(game);
		return listGame;
	}
	
	public List<GameResponse> retrieveGamesByRating(String code) {
		Rating rating = ratingService.getRating(code);
		List<Game> game = gameRepository.findAllByRatingCodeAndIsPublished(rating, true);
		List<GameResponse> listGame = GameMapper.ListGameToListGameResponse(game);
		return listGame;
	}
	
	public List<GameResponse> retrieveGamesByPublisher(Long id){
		Publisher publisher = publisherService.retrievePublisher(id);
		List<Game> game =  gameRepository.findAllByPublisherAndIsPublished(publisher,true);
		List<GameResponse> listGame = GameMapper.ListGameToListGameResponse(game);
		return listGame;
	}
	
	public UserPrincipal getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		return currentUser;
	}
	@LogActivity(activity="Unpublish a Game")
	@Transactional
	public void unpublishGame(@CurrentUser UserPrincipal currentUser, Long id) {
		User user = userService.retrieveUser(currentUser.getId());
		Game game = this.retrieveGame(id);
		gameValidation.unpublishGameValidator(game, user);
		gameRepository.unpublish(id);
	}
	
	public List<GameResponse> retrievePublishedGames(@CurrentUser UserPrincipal currentUser){
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		List<Game> game = gameRepository.findAllByPublisher(publisher);
		List<GameResponse> listGame = GameMapper.ListGameToListGameResponse(game);
		return listGame;
	}
	
	public List<GameResponse> retrieveGamesActive(){
		List<Game> game = gameRepository.findAllByIsPublished(true);
		List<GameResponse> listGame = GameMapper.ListGameToListGameResponse(game);
		return listGame;
	}
	
	public GameDetailResponse retrieveGameDetail(Long id, @CurrentUser UserPrincipal currentUser) {
		Game game = this.retrieveGame(id);
		PublisherResponse publisher = new PublisherResponse(game.getPublisher());
		ReviewResponse review = reviewService.retieveReviewByGameAsResponse(id);
		GameDetailResponse gameDetail = new GameDetailResponse(game, publisher, review);
		return gameDetail;
	}
	@LogActivity(activity="Edit Game Description")
	public Game updateGameDescription(@CurrentUser UserPrincipal currentUser, Long id, GameRequest input) {
		User user = userService.retrieveUser(currentUser.getId());
		Game game = this.retrieveGame(id);
		Publisher publisher = user.getPublisher();
		if(game.getPublisher()!=publisher) {
			throw new ForbiddenException("You have no right to update this game.");
		}
		game.setGameDescription(input.getGameDescription());
		return gameRepository.save(game);
	}
	
	
}
