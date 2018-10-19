package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;

public class GenreRequest {
	@NotBlank
	private String genreName;
	private String description;
	
	public GenreRequest() {
		
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
