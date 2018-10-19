package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.Genre;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.repository.GenreRepository;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
@Component
public class GenreValidation {
	@Autowired
	GenreRepository genreRepository;
	
	public void genreValidation(Long genreId) {
		Optional<Genre> genre = genreRepository.findById(genreId);
		if(!genre.isPresent()) {
			throw new ClassNotFoundException("Genre with id : " + genreId+" doesn't exist", ExceptionEnum.CLASS_NOT_FOUND);
		}
	}
}
