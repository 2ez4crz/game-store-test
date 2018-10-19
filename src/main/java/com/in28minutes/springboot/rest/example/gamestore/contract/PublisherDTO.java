package com.in28minutes.springboot.rest.example.gamestore.contract;

import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;

public class PublisherDTO {
	private Long id;
	
	private String publisherName;
	
	private Float sellingBalance;

	public PublisherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PublisherDTO(String publisherName, Float sellingBalance) {
		this.publisherName = publisherName;
		this.sellingBalance = sellingBalance;
	}
	public PublisherDTO(Publisher publisher) {
		this.id = publisher.getId();
		this.publisherName = publisher.getPublisherName();
		this.sellingBalance = publisher.getSellingBalance();
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

	public Float getSellingBalance() {
		return sellingBalance;
	}

	public void setSellingBalance(Float sellingBalance) {
		this.sellingBalance = sellingBalance;
	}
	public void setPublisherDTObyPublisher(Publisher publisher) {
		this.id = publisher.getId();
		this.publisherName = publisher.getPublisherName();
		this.sellingBalance = publisher.getSellingBalance();
	}
}
