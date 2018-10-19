package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalAmountRequest;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.Status;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.WithdrawalType;

@Entity
@Table(name="withdrawal")
public class Withdrawal {
	@Id
	@SequenceGenerator(name="withdrawal_id", sequenceName="withdrawal_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="withdrawal_id")
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="publisher_id", nullable=false)
	private Publisher publisher;
	
	@Column(name="amount", nullable=false)
	private float amount;
	
	@Column(name="date", nullable=false)
	private Date date;
	
	@Column(name="withdrawal_type", nullable=false)
	@Enumerated(EnumType.STRING)
	private WithdrawalType withdrawalType;
	
	@Column(name="status", nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Withdrawal() {
		
	}
	public Withdrawal(User user, Publisher publisher, float amount, WithdrawalType withdrawalType, Status status) {
		this.user = user;
		this.publisher = publisher;
		this.amount = amount;
		this.date = new Date();
		this.withdrawalType = withdrawalType;
		this.status = status;
	}
	public Withdrawal(WithdrawalAmountRequest input) {
			this.amount = input.getAmount();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public WithdrawalType getWithdrawalType() {
		return withdrawalType;
	}

	public void setWithdrawalType(WithdrawalType withdrawalType) {
		this.withdrawalType = withdrawalType;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
