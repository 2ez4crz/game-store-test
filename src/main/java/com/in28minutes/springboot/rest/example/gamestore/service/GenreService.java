package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.GenreRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Genre;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.GenreRepository;

@Service
public class GenreService {
	@Autowired
	GenreRepository genreRepository;
	@LogActivity(activity="Add a genre")
	public Genre addGenre(UserPrincipal currentUser, GenreRequest input) {
		Genre genre = new Genre(input);
		Genre savedGenre = genreRepository.save(genre);
		return savedGenre;
	}
	
	public List<Genre> retrieveAllGenre(){
		return genreRepository.findAll();
	}
	public Genre retrieveGenre(Long id) {
		Optional<Genre> genre = genreRepository.findById(id);
		if(!genre.isPresent())
			throw new ClassNotFoundException("Genre with id : "+id+" doesn't exist", ExceptionEnum.CLASS_NOT_FOUND);
		return genre.get();
	}
	@LogActivity(activity="Delete a genre")
	public Genre deleteGenre(UserPrincipal currentUser,Long id) {
		Genre deletedGenre = this.retrieveGenre(id);
		genreRepository.deleteById(id);
		return deletedGenre;
	}
}
