package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.PublisherBankAccount;

@Repository
public interface PublisherBankAccountRepository extends JpaRepository<PublisherBankAccount, Long> {

	@Modifying(clearAutomatically = true)
	@Query(value = "update PublisherBankAccount p SET p.isActive = ?2 where p.id = ?1")
	void setBankAccountStatus(Long id, boolean isActive);
	
	
}
