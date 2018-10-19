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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.MessageWithIdResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.TransactionDetailResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.TransactionTopUpRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionGamePurchase;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionTopUp;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/transaction/topup")
	@PreAuthorize("hasRole('ROLE_NORMAL_USER')")
	public ResponseEntity<Object> topUp(@Valid @RequestBody TransactionTopUpRequest input, @CurrentUser UserPrincipal currentUser) {
		TransactionTopUp savedTopUp = transactionService.doTransactionTopUp(currentUser, input.getVoucherCode());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/transaction/topup")
				.buildAndExpand(savedTopUp.getId()).toUri();
		return ResponseEntity.created(location).body("Top Up with transaction code : "+savedTopUp.getTransaction().getInvoice()+" success!");
	}
	@PostMapping("/transaction/game/{id}/purchase")
	@PreAuthorize("hasRole('ROLE_NORMAL_USER')")
	public ResponseEntity<Object> gamePurchase(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
		TransactionGamePurchase savedPurchase = transactionService.doGamePurchase(currentUser, id);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/transaction/game/{id}/purchase")
				.buildAndExpand(savedPurchase.getId()).toUri();
		return ResponseEntity.created(location).body(new MessageWithIdResponse(savedPurchase.getTransaction().getInvoice(), "Transaction with transaction code : "+savedPurchase.getTransaction().getInvoice()+" success!"));
	}
	@GetMapping("user/transaction/history")
	@PreAuthorize("hasRole('ROLE_NORMAL_USER')")
	public List<TransactionDetailResponse> transactionHistory(@CurrentUser UserPrincipal currentUser) {
		return transactionService.transactionHistory(currentUser);
	}
	@GetMapping("transaction/{invoice}")
	public TransactionDetailResponse transactionDetail(@PathVariable String invoice, @CurrentUser UserPrincipal currentUser) {
		return transactionService.RetrieveTransactionDetail(invoice, currentUser);
	}
}
