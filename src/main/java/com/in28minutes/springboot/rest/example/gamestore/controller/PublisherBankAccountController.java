package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherBankAccountRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.PublisherBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.PublisherBankAccountService;

@RestController
@RequestMapping("api/publisher/bank-account")
public class PublisherBankAccountController {

	@Autowired
	PublisherBankAccountService bankAccountService;
	
	@PostMapping("add")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public ResponseEntity<Object> addBankAccount(@RequestBody PublisherBankAccountRequest input, @CurrentUser UserPrincipal currentUser){
		PublisherBankAccount saved = bankAccountService.addBankAccount(currentUser, input);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/publisher/add/bank-account")
				.buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(location).body("Bank account added successfully!");
	}
}
