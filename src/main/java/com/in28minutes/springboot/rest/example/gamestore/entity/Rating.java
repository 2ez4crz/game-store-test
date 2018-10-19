package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.in28minutes.springboot.rest.example.gamestore.contract.RatingDTO;

@Entity
@Table(name="tb_rating")
public class Rating {
	@Id
	private String code;
	
	@Column(name="rating_name", nullable=false)
	private String ratingName;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="age_restriction", nullable=false)
	private int ageRestriction;

	public Rating() {
		
	}
	public Rating(RatingDTO input) {
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
	public void setUpdatedRating(RatingDTO input) {
		this.description = input.getDescription();
		this.ageRestriction = input.getAgeRestriction();
	}
	
}
