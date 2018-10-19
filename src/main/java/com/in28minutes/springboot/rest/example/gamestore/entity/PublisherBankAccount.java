package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="publisher_bank_account")
public class PublisherBankAccount {
	@Id
	@SequenceGenerator(name="bank_account_id", sequenceName="bank_account_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bank_account_id")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="publisher_id", nullable=false)
	private Publisher publisher;
	
	@ManyToOne
	@JoinColumn(name="bank_id", nullable=false)
	private Bank bank;
	
	@Column(name="accountNumber", nullable=false)
	private String accountNumber;

	@Column(name="is_active", nullable=false)
	private boolean isActive;
	
	public PublisherBankAccount(Publisher publisher, Bank bank, String accountNumber) {
		this.publisher = publisher;
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.isActive = true;
	}
	public PublisherBankAccount() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}
