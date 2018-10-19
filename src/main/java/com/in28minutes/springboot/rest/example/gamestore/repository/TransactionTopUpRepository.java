package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionTopUp;

@Repository
public interface TransactionTopUpRepository extends JpaRepository<TransactionTopUp, Long> {
	
	@Query(value="select COALESCE(MAX(t.id),0) from TransactionTopUp t")
	long getHighestData();
	
	Optional<TransactionTopUp> findByTransaction(Transaction transaction);
}
