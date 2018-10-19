package com.in28minutes.springboot.rest.example.gamestore.contract;

import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;

public class VoucherResponse {
	private String code;
	private float voucherBalance;
	private boolean isActive;
	public VoucherResponse() {
		
	}
	public VoucherResponse(Voucher input) {
		this.code = input.getCode();
		this.voucherBalance = input.getVoucherType().getValue();
		this.isActive = input.isActive();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getVoucherBalance() {
		return voucherBalance;
	}
	public void setVoucherBalance(float voucherBalance) {
		this.voucherBalance = voucherBalance;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
