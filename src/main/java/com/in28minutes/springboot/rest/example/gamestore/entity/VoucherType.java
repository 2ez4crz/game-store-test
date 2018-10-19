package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_voucher_type")
public class VoucherType {
	@Id
	private Long id;
	
	@Column(name="value", nullable=false)
	private float value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	
}
