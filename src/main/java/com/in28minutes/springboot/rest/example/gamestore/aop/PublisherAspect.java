package com.in28minutes.springboot.rest.example.gamestore.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherDTO;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.service.ChangeHistoryService;
import com.in28minutes.springboot.rest.example.gamestore.service.UserService;

@Aspect
@Component
public class PublisherAspect {

	@Autowired
	private UserService userService;
	@Autowired
	private ChangeHistoryService changeHistoryService;
	
	@Around("@annotation(PublisherChangeRecorder)")
	public Object PublisherChangeRecord(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] param = joinPoint.getArgs();
		UserPrincipal currentUser = (UserPrincipal) param[1];
		User user = userService.retrieveUser(currentUser.getId());
		
		PublisherDTO oldPublisher = new PublisherDTO();
		if(user.getPublisher()!=null) {
			oldPublisher.setPublisherDTObyPublisher(user.getPublisher());
		}
		Object proceed = joinPoint.proceed();
	    Publisher publisher = (Publisher) proceed;
	    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		PublisherChangeRecorder annotation = method.getAnnotation(PublisherChangeRecorder.class);
		if(oldPublisher.equals(null)) {
		    if(!publisher.getPublisherName().equals(oldPublisher.getPublisherName())) {
		    	changeHistoryService.addNewHistory("tb_publisher", "publisher_name", user, oldPublisher.getPublisherName(), publisher.getPublisherName(), annotation.activity());
		    }
		    if(publisher.getSellingBalance()!=oldPublisher.getSellingBalance()) {
		    	String oldBalance = String.valueOf(oldPublisher.getSellingBalance());
		    	String newBalance = String.valueOf(publisher.getSellingBalance());
		    	changeHistoryService.addNewHistory("tb_publisher", "selling_balance", user, oldBalance, newBalance, annotation.activity());
		    }
		}else {
			String id = String.valueOf(publisher.getId());
	    	String balance = String.valueOf(publisher.getSellingBalance());
			changeHistoryService.addNewHistory("tb_publisher", "id", user, null, id, annotation.activity());
			changeHistoryService.addNewHistory("tb_publisher", "publisher_name", user, null, publisher.getPublisherName(), annotation.activity());
			changeHistoryService.addNewHistory("tb_publisher", "selling_balance", user, null, balance, annotation.activity());
			
		}
	    return proceed;
	}
		
}
