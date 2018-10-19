package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update Voucher v SET v.isActive = false where v.code=?1")
	void deactive(String code);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update Voucher v SET v.isActive = true where v.code=?1")
	void setActive(String Code);
}
