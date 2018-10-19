package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.Rating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
	@Modifying(clearAutomatically = true)
	@Query(value = "update Game g SET g.purchased = ?2 where g.id = ?1")
	void setPurchased(Long gameId, Long puchased);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update Game g SET g.isPublished = false where g.id = ?1")
	void unpublish(Long gameId);
	
	
	List<Game> findAllByRatingCodeAndIsPublished(Rating ratingCode, boolean isPublished);
	
	List<Game> findAllByPublisher(Publisher publisher);
	
	List<Game> findAllByPublisherAndIsPublished(Publisher publisher, boolean isPublished);
	
	List<Game> findAllByIsPublished(boolean isPublished);
//	List<Game> findAllByGenre(Set<Genre> genre);
}
