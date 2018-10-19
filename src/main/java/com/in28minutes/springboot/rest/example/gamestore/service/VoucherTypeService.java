package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.entity.VoucherType;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.VoucherTypeRepository;

@Service
public class VoucherTypeService {
	@Autowired
	VoucherTypeRepository voucherTypeRepository;
	
	public VoucherType RetrieveVoucher(Long id) {
		Optional<VoucherType> voucherType = voucherTypeRepository.findById(id);
		
		if(!voucherType.isPresent()) {
			throw new ClassNotFoundException("Voucher Type not found.", ExceptionEnum.CLASS_NOT_FOUND);
		}
		
		return voucherType.get();
	}
}
