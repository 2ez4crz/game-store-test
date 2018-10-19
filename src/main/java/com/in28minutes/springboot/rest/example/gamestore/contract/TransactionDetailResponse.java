package com.in28minutes.springboot.rest.example.gamestore.contract;

import java.util.Date;

import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.TransactionType;

public class TransactionDetailResponse {
private String invoice;
	
	private UserResponse user;
	
	private float credit;
	
	private float debit;
	
	private Date transactionDate;
	
	private TransactionType transactionType;
	
	private Object detail;
	
	public TransactionDetailResponse() {
		
	}
	public TransactionDetailResponse(Transaction transaction, UserResponse user) {
		this.invoice = transaction.getInvoice();
		this.user = user;
		this.credit = transaction.getCredit();
		this.debit = transaction.getDebit();
		this.transactionType = transaction.getTransactionType();
		this.transactionDate = transaction.getTransactionDate();
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public UserResponse getUser() {
		return user;
	}
	public void setUser(UserResponse user) {
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
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public Object getDetail() {
		return detail;
	}
	public void setDetail(Object detail) {
		this.detail = detail;
	}
	
}
