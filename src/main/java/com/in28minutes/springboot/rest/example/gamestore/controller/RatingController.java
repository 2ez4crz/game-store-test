package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;
import java.util.List;

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

import com.in28minutes.springboot.rest.example.gamestore.contract.RatingDTO;
import com.in28minutes.springboot.rest.example.gamestore.entity.Rating;
import com.in28minutes.springboot.rest.example.gamestore.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	@Autowired
	RatingService ratingService;
	
	@GetMapping("/{id}")
	public RatingDTO getRating(@PathVariable("id") String id) {
		Rating rating = ratingService.getRating(id);
		RatingDTO result = new RatingDTO(rating);
		return result;
	}
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> addRating(@RequestBody RatingDTO rating) {
		Rating savedRating = ratingService.addRating(rating);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/rating/add")
				.buildAndExpand(savedRating.getCode()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> updateRating(@RequestBody RatingDTO rating) {
		Rating savedRating = ratingService.updateRating(rating);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/rating/update")
				.buildAndExpand(savedRating.getCode()).toUri();
		return ResponseEntity.ok(location);
	}
	@GetMapping("")
	public List<Rating> retrieveRating(){
		return ratingService.retrieveRating();
	}
//	@DeleteMapping("/delete/{code}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ResponseEntity<Object> deleteRating(@PathVariable String code) {
//		Rating rating = ratingService.deleteRating(code);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/rating/add")
//				.buildAndExpand(rating.getCode()).toUri();
//		return ResponseEntity.ok(location);
//	}
}
