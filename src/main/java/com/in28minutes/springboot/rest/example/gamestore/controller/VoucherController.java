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

import com.in28minutes.springboot.rest.example.gamestore.contract.VoucherRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.VoucherResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;
import com.in28minutes.springboot.rest.example.gamestore.mapper.VoucherMapper;
import com.in28minutes.springboot.rest.example.gamestore.service.VoucherService;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {
	@Autowired
	VoucherService voucherService;
	@Autowired
	VoucherMapper voucherMapper;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> addVoucher(@Valid @RequestBody VoucherRequest voucher) {
		Voucher savedVoucher = voucherService.addVoucher(voucher);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/voucher/add")
				.buildAndExpand(savedVoucher.getCode()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<VoucherResponse> retrieveVoucher(){
		List<Voucher> voucher = voucherService.retrieveVoucher();
		return voucherMapper.ListVoucherToListVoucherResponse(voucher);
	}
}
