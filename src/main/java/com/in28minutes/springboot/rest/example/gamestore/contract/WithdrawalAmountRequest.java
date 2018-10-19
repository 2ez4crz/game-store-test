package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WithdrawalAmountRequest {
	@NotNull
	private float amount;
	@NotBlank
	private String password;
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
