package com.in28minutes.springboot.rest.example.gamestore.contract;

import com.in28minutes.springboot.rest.example.gamestore.entity.Withdrawal;

public class WithdrawalBalanceResponse {
	private long userId;
	private float userBalance;
	private float publisherBslance;
	private float amountWithdrawed;
	private String message;
	public WithdrawalBalanceResponse(float userBalance, float publisherBalance, float amountWithdrawed, String message) {
		this.userBalance = userBalance;
		this.publisherBslance = publisherBalance;
		this.amountWithdrawed = amountWithdrawed;
		this.message = message;
	}
	public WithdrawalBalanceResponse(Withdrawal input) {
		this.userId = input.getUser().getId();
		this.userBalance = input.getUser().getBalance();
		this.publisherBslance = input.getPublisher().getSellingBalance();
		this.amountWithdrawed = input.getAmount();
		this.message = "Withdrawal Success";
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public float getUserBalance() {
		return userBalance;
	}
	public void setUserBalance(float userBalance) {
		this.userBalance = userBalance;
	}
	public float getPublisherBslance() {
		return publisherBslance;
	}
	public void setPublisherBslance(float publisherBslance) {
		this.publisherBslance = publisherBslance;
	}
	public float getAmountWithdrawed() {
		return amountWithdrawed;
	}
	public void setAmountWithdrawed(float amountWithdrawed) {
		this.amountWithdrawed = amountWithdrawed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
