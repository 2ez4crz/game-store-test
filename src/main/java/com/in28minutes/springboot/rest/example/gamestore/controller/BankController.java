package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.BankRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Bank;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	@Autowired
	BankService bankService;
	
	@GetMapping("/active")
	public List<Bank> retrieveAllActiveBanks(){
		return bankService.retrieveAllActiveBanks();
	}
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	public ResponseEntity<Object> addBank(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody BankRequest bank){
		Bank savedBank = bankService.addBank(currentUser, bank);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/withdrawal/mybalance")
				.buildAndExpand(savedBank.getId()).toUri();
		return ResponseEntity.created(location).body("SUCCESS");
	}
}
