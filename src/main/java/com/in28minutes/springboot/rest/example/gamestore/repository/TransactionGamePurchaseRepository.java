package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionGamePurchase;

@Repository
public interface TransactionGamePurchaseRepository extends JpaRepository<TransactionGamePurchase, Long> {
	@Query(value="select COALESCE(MAX(t.id),0)  from TransactionGamePurchase t")
	long getHighestData();
	
	Optional<TransactionGamePurchase> findByTransaction(Transaction transcaction);
}
