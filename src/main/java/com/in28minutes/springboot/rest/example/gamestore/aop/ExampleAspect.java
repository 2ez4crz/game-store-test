package com.in28minutes.springboot.rest.example.gamestore.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.LoginRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.WithdrawalRequest;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.Status;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.security.JwtAuthenticationFilter;
import com.in28minutes.springboot.rest.example.gamestore.service.UserActivityService;

@Aspect
@Component
public class ExampleAspect {
	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Autowired
	UserActivityService userActivityService;
	@Autowired
	PasswordEncoder passwordEncoder;
//	@Around("@annotation(LogActivity)")
//	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//		LOG.debug("Starting...");
//		//getting userDTO from the argument
//		Object[] param = joinPoint.getArgs();
//		UserPrincipal user = (UserPrincipal) param[1];
//		
//		Object proceed = joinPoint.proceed();
//	    GameDetailResponse game = (GameDetailResponse) proceed;
//	    userActivityService.addUserActivity(user, "View Game Detail with game id : "+game.getId(), Status.SUCCESS);
//	    LOG.debug("Activity Recorded");
//	    return proceed;
//	}
	@Around("@annotation(LogActivity)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object proceed = joinPoint.proceed();  
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LogActivity logActivity = method.getAnnotation(LogActivity.class);
		
	    Object[] args = joinPoint.getArgs();
	    if(logActivity.isAuthenticated()) {
	    	UserPrincipal user = (UserPrincipal) args[0];
			userActivityService.addUserActivity(user.getUsername(), logActivity.activity(), Status.SUCCESS);
	    }else {
	    	LoginRequest loginRequest = (LoginRequest) args[0];
			userActivityService.addUserActivity(loginRequest.getUsernameOrEmail(), logActivity.activity(), Status.SUCCESS);
	    }
	    LOG.debug("Activity Recorded");
	    return proceed;
	}
	@AfterThrowing("@annotation(LogActivity)")
	public void logFailed(JoinPoint joinPoint) {
//		Object[] param = joinPoint.getArgs();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LogActivity logActivity = method.getAnnotation(LogActivity.class);
		
		Object[] args = joinPoint.getArgs();
		
		if(logActivity.isAuthenticated()) {
	    	UserPrincipal user = (UserPrincipal) args[0];
			userActivityService.addUserActivity(user.getUsername(), logActivity.activity(), Status.FAILED);
	    }else {
	    	LoginRequest loginRequest = (LoginRequest) args[0];
			userActivityService.addUserActivity(loginRequest.getUsernameOrEmail(), logActivity.activity(), Status.FAILED);
	    }
	    LOG.error("ERROR");
	}
	
}
