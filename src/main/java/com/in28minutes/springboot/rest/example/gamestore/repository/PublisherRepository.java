package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{

	
	@Modifying(clearAutomatically = true)
	@Query(value = "update Publisher p SET p.sellingBalance = ?2 where p.id = ?1")
	void setBalance(Long publisherId, float sellingBalance);
	
//	Optional<Publisher> findByUser(User user);
}
