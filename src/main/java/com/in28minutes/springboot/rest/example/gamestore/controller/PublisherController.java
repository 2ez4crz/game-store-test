package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherProfileResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.mapper.PublisherMapper;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.PublisherService;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

	@Autowired
	PublisherService publisherService;
	@Autowired
	PublisherMapper publisherMapper;
	
	@GetMapping("")
	public List<PublisherResponse> retrieveAllPublisher(){
		List<Publisher> publisher = publisherService.retrievePublishers();
		return publisherMapper.ListPublisherToListPublisherResponse(publisher);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_NORMAL_USER')")
	public ResponseEntity<Object> createPublisher(@Valid @RequestBody PublisherRequest publisher, @CurrentUser UserPrincipal currentUser) {
		Publisher savedPublisher = publisherService.createPublisher(currentUser, publisher);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/publisher/add")
				.buildAndExpand(savedPublisher.getId()).toUri();
		return ResponseEntity.created(location).body("Publisher registered successfully!");
	}
	@GetMapping("/{id}")
	public PublisherResponse retrievePublisher(@PathVariable Long id){
		PublisherResponse publisher =  new PublisherResponse(publisherService.retrievePublisher(id));
		return publisher;
	}
	
	@GetMapping("/myProfile")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public PublisherProfileResponse retrieveMyProfile(@CurrentUser UserPrincipal currentUser) {
		return publisherService.retrieveMyProfile(currentUser);
	}
	@PutMapping("/myProfile/edit")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public  ResponseEntity<Object> updatePublisher(@RequestBody PublisherRequest input, @CurrentUser UserPrincipal currentUser){
		publisherService.updatePublisher(currentUser, input);
		return ResponseEntity.ok().body("Publisher info updated successfully!");
	}
}
