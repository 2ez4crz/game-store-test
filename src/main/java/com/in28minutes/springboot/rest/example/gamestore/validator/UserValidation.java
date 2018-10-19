package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.BadRequestException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.UserRepository;
import com.in28minutes.springboot.rest.example.gamestore.util.DateUtil;
@Component
public class UserValidation {
	@Autowired 
	UserRepository userRepository;
	
	public void isUserIdAlreadyExistValidator(Long userId) {	
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			throw new ClassNotFoundException("User with id : "+ userId+" doesn't exist",ExceptionEnum.CLASS_NOT_FOUND);
		}
	}
	public void isUsernameAlreadyExistValidator(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			throw new BadRequestException("username already exist.",ExceptionEnum.USERNAME_ALREADY_EXIST);
		}
	}
	
	public void isEmailAlreadyExistValidator(String email) {
		Optional<User> user = userRepository.getByEmail(email);
		if(user.isPresent()) {
			throw new BadRequestException("email already exist.",ExceptionEnum.EMAIL_ALREADY_EXIST);
		}
	}
	public void signUpAgeValidator(User user) {
		int age = DateUtil.getAge(user.getBirthDate());
		if(age<5){
			throw new ForbiddenException("User must be at least 5 years old or older.",ExceptionEnum.AGE_RESTRICTED);
		}
	}
	public void signUpValidator(User user) {
		if(user.getUsername().isEmpty()||user.getName().isEmpty()||user.getPassword().isEmpty()||user.getBirthDate()==null||user.getEmail().isEmpty()) {
			throw new BadRequestException("data can't be null.",ExceptionEnum.MUST_NOT_NULL);
		}
		if(user.getUsername().length()<6 || !user.getUsername().matches("[a-zA-Z0-9]*") || user.getUsername().length()>16) {
			throw new BadRequestException("Username must be 6-16 digit of letters or numbers.",ExceptionEnum.INVALID_INPUT);
		}
		if(user.getPassword().length()<8 ||  user.getPassword().length()>16) {
			throw new BadRequestException("Password must be 8-16 digit.", ExceptionEnum.INVALID_INPUT);
		}
		this.signUpAgeValidator(user);
		this.isUsernameAlreadyExistValidator(user.getUsername());
		this.isEmailAlreadyExistValidator(user.getEmail());
	}
	
}
