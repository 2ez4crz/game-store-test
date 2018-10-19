package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
//	@Query(value = "select a from Genre a JOIN GameGenre b ON b.genreId = a.id WHERE b.gameId = ?1")
//	List<Genre> getByGame(Long gameId);
}
