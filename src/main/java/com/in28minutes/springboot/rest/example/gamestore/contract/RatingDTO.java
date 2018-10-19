package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.in28minutes.springboot.rest.example.gamestore.entity.Rating;

public class RatingDTO {
	@NotBlank
	private String code;
	@NotBlank
	private String ratingName;
	
	private String description;
	@NotNull
	private int ageRestriction;
	public RatingDTO() {
		
	}
	public RatingDTO(Rating input) {
		this.code = input.getCode();
		this.ratingName= input.getRatingName();
		this.description = input.getDescription();
		this.ageRestriction = input.getAgeRestriction();
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRatingName() {
		return ratingName;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAgeRestriction() {
		return ageRestriction;
	}

	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}
	
}
