package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.VoucherRepository;

@Component
public class VoucherValidation {

	@Autowired
	VoucherRepository voucherRepository;
	
	public void isVoucherCodeAlreadyExistValidation(String voucherCode) {
		Optional<Voucher> voucher = voucherRepository.findById(voucherCode);
		if(voucher.isPresent()) {
			throw new BadRequestException("Double voucher code detected!", ExceptionEnum.VOUCHER_ALREADY_EXIST);
		}
	}
}
