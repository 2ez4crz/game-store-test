package com.in28minutes.springboot.rest.example.gamestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.VoucherResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;

@Component
public class VoucherMapper {
	public List<VoucherResponse> ListVoucherToListVoucherResponse(List<Voucher> voucher){
		List<VoucherResponse> result = new ArrayList<VoucherResponse>();
		for(int i =0; i<voucher.size();i++){
			VoucherResponse newVoucher = new VoucherResponse(voucher.get(i));
			result.add(newVoucher);
		}
		return result;
	}
}
