package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.LoginRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.SignupRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Role;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.security.JwtAuthenticationResponse;
import com.in28minutes.springboot.rest.example.gamestore.service.RoleService;
import com.in28minutes.springboot.rest.example.gamestore.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService; 
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        String jwt = userService.signin(loginRequest);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
	@PostMapping("/signup")
	public ResponseEntity<Object> createUsers(@Valid @RequestBody SignupRequest input){
		User savedUser = userService.createUser(input);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/signup")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).body("Registration success!");
	}
	@GetMapping("/password/{password}")
    public String encodePass(@PathVariable String password) {
		return userService.testEncode(password);
    }
	@PostMapping("/role/add")
	public ResponseEntity<Object> addRole(@RequestBody Role role){
		Role savedRole = roleService.addRole(role);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/role/add")
				.buildAndExpand(savedRole.getId()).toUri();
		return ResponseEntity.created(location).body("add role success!");
	}
}
