package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.List;

import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.PublisherBankAccount;

public class PublisherProfileResponse {
	private Long id;
	private String publisherName;
	private Float sellingBalance;
	private String ownerName;
	private List<PublisherBankAccount> bankAccount;
	private List<GameResponse> publishedGames;
	public PublisherProfileResponse() {
		
	}
	public PublisherProfileResponse(Publisher publisher, String ownerName, List<GameResponse> publishedGames) {
		this.id = publisher.getId();
		this.publisherName = publisher.getPublisherName();
		this.sellingBalance = publisher.getSellingBalance();
		this.ownerName = ownerName;
		this.bankAccount = publisher.getBankAccount();
		this.publishedGames = publishedGames;
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
	public List<PublisherBankAccount> getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(List<PublisherBankAccount> bankAccount) {
		this.bankAccount = bankAccount;
	}
	public List<GameResponse> getPublishedGames() {
		return publishedGames;
	}
	public void setPublishedGames(List<GameResponse> publishedGames) {
		this.publishedGames = publishedGames;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
}
