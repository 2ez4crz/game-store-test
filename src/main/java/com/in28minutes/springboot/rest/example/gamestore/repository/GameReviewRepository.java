package com.in28minutes.springboot.rest.example.gamestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameReview;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;

@Repository
public interface GameReviewRepository extends JpaRepository<GameReview, Long> {
	Optional<GameReview> findByUserAndGame(User user, Game game);
	List<GameReview> findAllByGame(Game game);
}
