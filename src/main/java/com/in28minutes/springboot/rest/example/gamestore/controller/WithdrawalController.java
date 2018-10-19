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

import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalAmountRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalBalanceResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalBankResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalRequest;
import com.in28minutes.springboot.rest.example.gamestore.entity.Withdrawal;
import com.in28minutes.springboot.rest.example.gamestore.entity.WithdrawalBankAccount;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.WithdrawalService;

@RestController
@RequestMapping("/api")
public class WithdrawalController {
	@Autowired
	WithdrawalService withdrawalService;
	
	@GetMapping("withdrawal")
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	public List<Withdrawal> retrieveAllWithdrawal(){
		return withdrawalService.retrieveAllWithdrawal();
	}
	
	@GetMapping("withdrawal/history")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")	
	public List<Withdrawal> retrieveAllWithdrawal(@CurrentUser UserPrincipal currentUser){
		return withdrawalService.retrieveWithdrawalHistory(currentUser);
	}
	
	@PostMapping("withdrawal/mybalance")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")	
	public ResponseEntity<Object> withdrawToBalance(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody WithdrawalAmountRequest input){
		Withdrawal savedWithdrawal = withdrawalService.withdrawToPrivateBalance(currentUser, input);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/withdrawal/mybalance")
				.buildAndExpand(savedWithdrawal.getId()).toUri();
		return ResponseEntity.created(location).body(new WithdrawalBalanceResponse(savedWithdrawal));
	}
	@PostMapping("withdrawal/bank")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")	
	public ResponseEntity<Object> withdrawToBank(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody WithdrawalRequest input){
		WithdrawalBankAccount savedWithdrawal = withdrawalService.withdrawToBankAccount(currentUser, input);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/withdrawal/bank")
				.buildAndExpand(savedWithdrawal.getId()).toUri();
		return ResponseEntity.created(location).body(new WithdrawalBankResponse(savedWithdrawal));
	}
}
