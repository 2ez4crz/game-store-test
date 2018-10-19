package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.GameReviewRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameReview;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.GameReviewService;

@RestController
@RequestMapping("/api")
public class GameReviewController {
	@Autowired
	GameReviewService gameReviewService;
	
	@GetMapping("/game/{gameId}/review")
	public Object retrieveGameReviewByGame(@PathVariable Long gameId){
		return gameReviewService.retieveReviewByGameAsResponse(gameId);
	}
	
	@PostMapping("/game/{id}/review/add")
	@PreAuthorize("hasRole('ROLE_NORMAL_USER')")
	public ResponseEntity<Object> addGameReview(@PathVariable Long id,@Valid @RequestBody GameReviewRequest review, @CurrentUser UserPrincipal currentUser){
		GameReview savedGameReview = gameReviewService.addReview(currentUser, id,review);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/game/{id}/review/add")
				.buildAndExpand(savedGameReview.getId()).toUri();
		return ResponseEntity.created(location).body("Review submited.");
	}
}
