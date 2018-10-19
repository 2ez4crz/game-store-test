package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotNull;

public class TransactionTopUpRequest {
	@NotNull
	private String voucherCode;

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	
}
