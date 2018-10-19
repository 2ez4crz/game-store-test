package com.in28minutes.springboot.rest.example.gamestore.contract;

import com.in28minutes.springboot.rest.example.gamestore.entity.WithdrawalBankAccount;

public class WithdrawalBankResponse {
	private Long userId;
	private float publisherBslance;
	private float amountWithdrawed;
	private String bankAccount;
	private String message;
	public WithdrawalBankResponse() {
	}
	public WithdrawalBankResponse(float publisherBslance, float amountWithdrawed, String bankAccount, String message) {
		super();
		this.publisherBslance = publisherBslance;
		this.amountWithdrawed = amountWithdrawed;
		this.bankAccount = bankAccount;
		this.message = message;
	}
	public WithdrawalBankResponse(WithdrawalBankAccount input) {
		this.userId = input.getWithdrawal().getUser().getId();
		this.publisherBslance = input.getWithdrawal().getPublisher().getSellingBalance();
		this.amountWithdrawed = input.getWithdrawal().getAmount();
		this.bankAccount = input.getBank().getBankName() + " - "+input.getBankAccountNumber();
		this.message = "Withdrawal Success!";
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
