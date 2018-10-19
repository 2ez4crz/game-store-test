package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.in28minutes.springboot.rest.example.gamestore.entity.FileType;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.FileStorageException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.repository.FileTypeRepository;

@Component
public class FileUploadValidation {
	@Autowired
	FileTypeRepository fileTypeRepository;
	
	public void isFileTypeValid(String fileType, String contentType){
		Optional<FileType> type = fileTypeRepository.findByFileTypeAndContentType(fileType, contentType);
		if(!type.isPresent()) {
			throw new FileStorageException("Invalid File Type.", ExceptionEnum.INVALID_FILE_TYPE);
		}
	}
	public void isFileSizeNotExceedLimit(float limit, long fileSize) {
		if(fileSize>limit) {
			throw new FileStorageException("max file size is "+ limit+" bytes", ExceptionEnum.FILE_EXCEED_LIMIT);
		}
	}
	
	public void updatePhotoProfileValidation(MultipartFile file) {
		this.isFileTypeValid("image", file.getContentType());
    	
	}
	public void updateGameImage(MultipartFile file,Game game, User user) {
		this.isFileTypeValid("image", file.getContentType());
    	if(game.getPublisher()!=user.getPublisher()) {
    		throw new ForbiddenException("You have no right to edit this game.", ExceptionEnum.ACCESS_DENIED);
    	}
    	
	}
}
