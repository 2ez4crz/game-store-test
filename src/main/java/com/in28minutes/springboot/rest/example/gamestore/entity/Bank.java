package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.in28minutes.springboot.rest.example.gamestore.contract.BankRequest;

@Entity
@Table(name="tb_bank")
public class Bank {
	@Id
	@SequenceGenerator(name="bank_id", sequenceName="bank_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bank_id")
	private int id;
	
	@Column(name="bank_name", nullable=false)
	private String bankName;
	
	@Column(name="status", nullable=false)
	private boolean status;

	public Bank() {
		
	}
	
	public Bank(BankRequest bank) {
		this.bankName = bank.getBankName();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
