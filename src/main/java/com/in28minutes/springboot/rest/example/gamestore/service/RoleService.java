package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.entity.Role;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	public Role RetrieveRole(int id) {
		Optional<Role> role = roleRepository.findById(id);
		if(!role.isPresent())
			throw new ClassNotFoundException("Role not found", ExceptionEnum.CLASS_NOT_FOUND);
		
		return role.get();
	}
	public Role addRole(Role role) {
		Role savedRole = roleRepository.save(role);
		return savedRole;
	}
}
