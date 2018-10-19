package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
	List<Transaction> findAllByUser(User user);
}
