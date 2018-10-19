package com.in28minutes.springboot.rest.example.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.entity.ChangeHistory;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.repository.ChangeHistoryRepository;

@Service
public class ChangeHistoryService {
	@Autowired
	ChangeHistoryRepository changeHistoryRepository;
	
	public ChangeHistory addNewHistory(String table, String column, User user, String before, String after,String description) {
		ChangeHistory data = new ChangeHistory(table, column, user, before, after, description);
		return changeHistoryRepository.save(data);
	}
}
