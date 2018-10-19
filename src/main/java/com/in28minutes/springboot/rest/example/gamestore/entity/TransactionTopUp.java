package com.in28minutes.springboot.rest.example.gamestore.entity;


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
@Table(name = "transaction_top_up")
public class TransactionTopUp {
	@Id
	@SequenceGenerator(name="top_up_id", sequenceName="top_up_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="top_up_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="transaction_invoice", nullable=false)
	private Transaction transaction;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="voucher_code", nullable=false)
	private Voucher voucher;

	public TransactionTopUp() {

	}

	public TransactionTopUp(Transaction transaction, Voucher voucher) {
		this.transaction = transaction;
		this.voucher = voucher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

}
