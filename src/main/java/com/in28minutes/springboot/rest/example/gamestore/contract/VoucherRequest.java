package com.in28minutes.springboot.rest.example.gamestore.contract;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class VoucherRequest {
	@NotBlank
	private String code;
	@NotNull
	private Long voucherType;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(Long voucherType) {
		this.voucherType = voucherType;
	}
	
}
