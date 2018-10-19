package com.in28minutes.springboot.rest.example.gamestore.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalAmountRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalRequest;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.security.JwtAuthenticationFilter;

@Aspect
@Component
public class PasswordValidation {
	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Autowired
	PasswordEncoder passwordEncoder;
	@Before("execution(* com.in28minutes.springboot.rest.example.gamestore.controller.WithdrawalController.withdrawToBank(..))")
	public void cekPasswordWithdrawalBank(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		UserPrincipal currentUser = (UserPrincipal) args[0];
		WithdrawalRequest input = (WithdrawalRequest) args[1];
		
		if(!passwordEncoder.matches(input.getPassword(), currentUser.getPassword())) {
			throw new ForbiddenException("Wrong password",ExceptionEnum.PASSWORD_MISMATCH);
		}
		
	}
	@Before("execution(* com.in28minutes.springboot.rest.example.gamestore.controller.WithdrawalController.withdrawToBalance(..))")
	public void cekPasswordWithdrawalBalance(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		UserPrincipal currentUser = (UserPrincipal) args[0];
		WithdrawalAmountRequest input = (WithdrawalAmountRequest) args[1];
		
		if(!passwordEncoder.matches(input.getPassword(), currentUser.getPassword())) {
			throw new ForbiddenException("Wrong password", ExceptionEnum.PASSWORD_MISMATCH);
		}
		
	}
}
