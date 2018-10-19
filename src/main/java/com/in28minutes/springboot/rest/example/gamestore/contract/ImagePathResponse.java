package com.in28minutes.springboot.rest.example.gamestore.contract;

public class ImagePathResponse {
	private String imagePath;

	public ImagePathResponse() {

	}

	public ImagePathResponse(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
