package com.in28minutes.springboot.rest.example.gamestore.contract;

import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;

public class PublisherResponse {
	private Long id;
	private String publisherName;
	
	public PublisherResponse() {
		
	}
	public PublisherResponse(Publisher publisher) {
		this.id = publisher.getId();
		this.publisherName = publisher.getPublisherName();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
}
