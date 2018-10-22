package com.in28minutes.springboot.rest.example.gamestore.enumeration;

public enum ImageFileTypes {
	JPEG("image/jpeg"),
	PNG("image/png");
	
	private String fileType;

	private ImageFileTypes(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}	
}
