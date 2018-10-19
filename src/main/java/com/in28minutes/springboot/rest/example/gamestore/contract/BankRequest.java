package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;

public class BankRequest {
	@NotBlank
	private String bankName;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
