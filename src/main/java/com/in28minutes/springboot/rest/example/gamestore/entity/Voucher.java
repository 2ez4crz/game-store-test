package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_voucher")
public class Voucher {
	@Id
	@Column(name="code", nullable=false)
	private String code;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="voucher_type_id", nullable=false)
	private VoucherType voucherType;
	
	@Column(name="is_active", nullable=false)
	private boolean isActive;
	
	
	public Voucher() {
		
	}
	
	public Voucher(String code, VoucherType voucherType) {
		this.code = code;
		this.voucherType = voucherType;
		this.isActive = true;
	}

	public VoucherType getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(VoucherType voucherType) {
		this.voucherType = voucherType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
