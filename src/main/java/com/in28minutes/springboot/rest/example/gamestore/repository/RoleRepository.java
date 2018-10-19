package com.in28minutes.springboot.rest.example.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.gamestore.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
