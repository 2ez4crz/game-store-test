package com.in28minutes.springboot.rest.example.gamestore.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="withdrawal_bank_account")
public class WithdrawalBankAccount {
	@Id
	@SequenceGenerator(name="withdrawal_bank_id", sequenceName="withdrawal_bank_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="withdrawal_bank_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="withdrawal_id", nullable=false)
	private Withdrawal withdrawal;
	
	@OneToOne()
	@JoinColumn(name="bank_id", nullable=false)
	private Bank bank;
	
//	@OneToOne()
//	@JoinColumn(name="admin_id", nullable=true)
//	private User adminId;
	
	@Column(name="bank_account_number", nullable=false)
	private String bankAccountNumber;
	
//	@Column(name="date_confirmed", nullable=true)
//	private Date dateConfirmed;
//	
	
	
	public WithdrawalBankAccount() {
	}

	public WithdrawalBankAccount( Withdrawal withdrawal, Bank bank, String bankAccountNumber) {
		
		this.withdrawal = withdrawal;
		this.bank = bank;
		this.bankAccountNumber = bankAccountNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Withdrawal getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(Withdrawal withdrawal) {
		this.withdrawal = withdrawal;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}


	
}
