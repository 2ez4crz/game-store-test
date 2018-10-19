package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.BaseResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameDetailResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {
	@Autowired
	GameService gameService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public ResponseEntity<?> createGame(@Valid @RequestBody GameRequest game, @CurrentUser UserPrincipal currentUser) {
		Game savedGame = gameService.createGame(currentUser, game);
		GameResponse data = new GameResponse(savedGame);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/game/add")
				.buildAndExpand(savedGame.getId()).toUri();
		return ResponseEntity.created(location).body(new BaseResponse(data,"Game Published Successfully"));
	}
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<GameResponse> retrieveGames() {
		return gameService.retrieveGames();
	}
	@GetMapping("")
	public List<GameResponse> retrieveGamesActive(){
		List<GameResponse> game =  gameService.retrieveGamesActive();
		return game;
	}
	@GetMapping("/rating/{code}")
	public List<GameResponse> retrieveGameByRating(@PathVariable String code) {
		return gameService.retrieveGamesByRating(code);
	}
	@GetMapping("/{id}")
	public GameDetailResponse retrieveGame(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
		return gameService.retrieveGameDetail(id, currentUser);
	}
	@GetMapping("/publisher/{id}")
	public List<GameResponse> retrieveGameByRating(@PathVariable Long id) {
		return gameService.retrieveGamesByPublisher(id);
	}
	@PutMapping("/{id}/unpublish")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public ResponseEntity<Object> unpublishGame(@PathVariable Long id, @CurrentUser UserPrincipal currentUser){
		gameService.unpublishGame(currentUser, id);
		return ResponseEntity.ok().body("Game Unpublished Successfully.");
	}
	@GetMapping("/published")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public List<GameResponse> retrieveGameByRating(@CurrentUser UserPrincipal currentUser) {
		return gameService.retrievePublishedGames(currentUser);
	}
	@PutMapping("/{id}/edit/description")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public ResponseEntity<Object> updateGameDetail(@PathVariable Long id,@RequestBody GameRequest input, @CurrentUser UserPrincipal currentUser){
		Game saved = gameService.updateGameDescription(currentUser, id, input);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/publisher/myProfile/edit")
				.buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.ok(location);
	}
	
}
