package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.contract.VoucherRequest;
import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;
import com.in28minutes.springboot.rest.example.gamestore.entity.VoucherType;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.VoucherRepository;
import com.in28minutes.springboot.rest.example.gamestore.validator.VoucherValidation;

@Service
public class VoucherService {
	@Autowired
	VoucherRepository voucherRepository;
	@Autowired
	VoucherTypeService voucherTypeService;
	@Autowired
	VoucherValidation voucherValidation;
	public Voucher RetrieveVoucher(String voucherCode) {
		Optional<Voucher> voucher = voucherRepository.findById(voucherCode);
		if(!voucher.isPresent()) {
			throw new ClassNotFoundException("Voucher not found.", ExceptionEnum.CLASS_NOT_FOUND);
		}
		return voucher.get();
	}
	
	public void setDeactive(String voucherCode) {
		voucherRepository.deactive(voucherCode);
	}
	
	public void setActive(String voucherCode) {
		voucherRepository.setActive(voucherCode);
	}
	public List<Voucher> retrieveVoucher(){
		return voucherRepository.findAll();
	}
	public Voucher addVoucher(VoucherRequest input) {
		if(input.getCode().length()!=16 || !input.getCode().matches("[a-zA-Z0-9]*")) {
			throw new BadRequestException("Voucher code must be 16 digit of letters or numbers.",ExceptionEnum.INVALID_INPUT);
		}
		VoucherType voucherType = voucherTypeService.RetrieveVoucher(input.getVoucherType());
		Voucher voucher = new Voucher(input.getCode().toUpperCase(), voucherType);
		voucherValidation.isVoucherCodeAlreadyExistValidation(voucher.getCode());
		voucher.setActive(true);
		voucher.setVoucherType(voucherType);
		return voucherRepository.save(voucher);
	}
}
