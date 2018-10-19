package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
	Optional<Bank> findByBankName(String bankName);
	
	List<Bank> findAllByStatus(Boolean status);
}
