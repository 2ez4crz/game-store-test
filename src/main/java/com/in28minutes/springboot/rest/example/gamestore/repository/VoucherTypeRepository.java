package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.VoucherType;

@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType, Long>{

}
