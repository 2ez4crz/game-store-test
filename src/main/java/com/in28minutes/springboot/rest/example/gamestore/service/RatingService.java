package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.contract.RatingDTO;
import com.in28minutes.springboot.rest.example.gamestore.entity.Rating;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepository;
	
	public Rating getRating(String id) {
		Optional<Rating> rating = ratingRepository.findById(id);
		if(!rating.isPresent())
			throw new ClassNotFoundException("Rating doesn't Exist.");
		return rating.get();
	}
	public Rating addRating(RatingDTO input) {
		if(input.getAgeRestriction()<0) {
			throw new BadRequestException("Number not valid");
		}
		Rating rating = new Rating(input);
		Rating savedRating = ratingRepository.save(rating);
		return savedRating;
	}
	public List<Rating> retrieveRating(){
		return ratingRepository.findAll();
	}
	public Rating updateRating(RatingDTO input) {
		Rating rating = this.getRating(input.getCode());
		rating.setUpdatedRating(input);
		Rating updatedRating = ratingRepository.save(rating);
		return updatedRating;
	}
	public Rating deleteRating(String code) {
		Rating rating = this.getRating(code);
		ratingRepository.delete(rating);
		return rating;
	}
}
