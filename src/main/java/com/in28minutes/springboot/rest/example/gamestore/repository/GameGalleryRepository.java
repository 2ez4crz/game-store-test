package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameGallery;

@Repository
public interface GameGalleryRepository extends JpaRepository<GameGallery, Long> {
	@Query(value = "select COALESCE(MAX(g.id),0) FROM GameGallery g")
	Long getNextId();
	
	List<GameGallery> findAllByGame(Game game);
}
