package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.GenreRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Genre;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
	@Autowired
	GenreService genreService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> addGenre(@CurrentUser UserPrincipal currentUser, @RequestBody GenreRequest genre) {
		Genre savedGenre = genreService.addGenre(currentUser, genre);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/genre/add")
				.buildAndExpand(savedGenre.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("")
	public List<Genre> retrieveAllGenre(){
		return genreService.retrieveAllGenre();
	}
	
	@DeleteMapping("/{id}//delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> deleteGenre(@CurrentUser UserPrincipal currentUser, @PathVariable long id) {
		Genre deletedGenre = genreService.deleteGenre(currentUser, id);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/genre/{id}")
				.buildAndExpand(deletedGenre.getId()).toUri();
		return ResponseEntity.ok(location);
	}
}
