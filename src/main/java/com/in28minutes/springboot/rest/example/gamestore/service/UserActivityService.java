package com.in28minutes.springboot.rest.example.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.entity.UserActivity;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.Status;
import com.in28minutes.springboot.rest.example.gamestore.repository.UserActivityRepository;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;

@Service
public class UserActivityService {
	@Autowired
	UserActivityRepository userActivityRepository;
	@Autowired
	UserService userService;
	public UserActivity addUserActivity(String user, String message, Status status) {
		UserActivity userActivity = new UserActivity(user,message, status);
		userActivityRepository.save(userActivity);
	    return userActivity;
	}
}
