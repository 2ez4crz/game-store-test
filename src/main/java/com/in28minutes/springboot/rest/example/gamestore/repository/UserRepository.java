package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.Role;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select u from User u where LOWER(u.username) like LOWER(?1)")
	Optional<User> getByUsername(String username);
	
	@Query(value = "select u from User u where LOWER(u.email) like LOWER(?1)")
	Optional<User> getByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update User u SET u.balance = ?2 where u.id = ?1")
	void setBalance(Long userId, float balance);

	@Modifying(clearAutomatically = true)
	@Query(value = "update User u SET u.publisher = ?2, u.roles = ?3 where u.id = ?1")
	void setPublisher(Long userId, Publisher publisher, Role role);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update User u SET u.password = ?2 where u.id = ?1")
	void setPassword(Long userId, String password);
	
	
	Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail2);
	
	Optional<User> findByPublisher(Publisher publisher);
	
	
//	@Modifying(clearAutomatically = true)
//	@Query(value = "update User u SET u.role = ?2 where u.id = ?1")
//	void setRole(Long userId, Role roleId);
	
}