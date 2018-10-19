package com.in28minutes.springboot.rest.example.gamestore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.repository.PublisherRepository;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
@Component
public class PublisherValidation {

	@Autowired
	PublisherRepository publisherRepository;
	public void isUserAlreadyBecomeAPublisherValidator(User user) {
		if(user.getPublisher()!=null) {
			throw new ForbiddenException("You're already registered as publisher", ExceptionEnum.USER_ALREADY_BECOME_PUBLISHER);
		}
	}
}
