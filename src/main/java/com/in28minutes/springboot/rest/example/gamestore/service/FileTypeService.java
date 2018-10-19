package com.in28minutes.springboot.rest.example.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.gamestore.repository.FileTypeRepository;

@Service
public class FileTypeService {
	@Autowired
	FileTypeRepository fileTypeRepository;
	
	
}
