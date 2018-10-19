package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;

public class PublisherBankAccountRequest {
	@NotBlank
	private int bankId;
	@NotBlank
	private String accountNumber;
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
