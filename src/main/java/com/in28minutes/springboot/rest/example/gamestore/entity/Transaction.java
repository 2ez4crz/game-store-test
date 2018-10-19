package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.in28minutes.springboot.rest.example.gamestore.enumeration.TransactionType;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	private String invoice;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@Column(name="credit", nullable=true)
	private float credit;
	
	@Column(name="debit", nullable=true)
	private float debit;
	
	@Column(name="transaction_date", nullable=false)
	private Date transactionDate;
	
	@Column(name="transaction_type", nullable=false)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	public Transaction() {
		
	}
	public Transaction(String invoice, User user, float credit, float debit,
			TransactionType transactionType) {
		super();
		this.invoice = invoice;
		this.user = user;
		this.credit = credit;
		this.debit = debit;
		this.transactionDate = new Date();
		this.transactionType = transactionType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
		this.debit = debit;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
}
