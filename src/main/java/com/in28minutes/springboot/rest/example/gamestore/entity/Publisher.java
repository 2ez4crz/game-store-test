package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherRequest;

@Entity
@Table(name = "tb_publisher")
public class Publisher {
	@Id
	@SequenceGenerator(name="publisher_id", sequenceName="publisher_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="publisher_id")
	private Long id;
	
	
	@Column(name="publisher_name", nullable= false)
	private String publisherName;
	
//	@OneToOne(fetch = FetchType.EAGER)
//    @Column(name="user_id")
//	private User user;
	
	@Column(name="selling_balance")
	private Float sellingBalance;
	
	@OneToMany(mappedBy="publisher")
	private List<PublisherBankAccount> bankAccount;
	
	public Publisher() {
		
	}
	public Publisher(PublisherRequest input) {
		this.publisherName = input.getPublisherName();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public float getSellingBalance() {
		return sellingBalance;
	}
	public void setSellingBalance(Float sellingBalance) {
		this.sellingBalance = sellingBalance;
	}
	public List<PublisherBankAccount> getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(List<PublisherBankAccount> bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	
}
