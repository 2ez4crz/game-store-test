package com.in28minutes.springboot.rest.example.gamestore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.in28minutes.springboot.rest.example.gamestore.entity.FileType;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.ImageFileTypes;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.FileStorageException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.repository.FileTypeRepository;

@Component
public class FileUploadValidation {
	@Autowired
	FileTypeRepository fileTypeRepository;
	
	public boolean isImageFileTypeValid(String contentType){
		boolean result = false; 
		for(ImageFileTypes i : ImageFileTypes.values()) {
			if(i.getFileType().equals(contentType)) {
				result = true;
			}
		}
		return result;
	}
	public void isFileSizeNotExceedLimit(float limit, long fileSize) {
		if(fileSize>limit) {
			throw new FileStorageException("max file size is "+ limit+" bytes", ExceptionEnum.FILE_EXCEED_LIMIT);
		}
	}
	
	public void updatePhotoProfileValidation(MultipartFile file) {
		if(!this.isImageFileTypeValid(file.getContentType())) {
			throw new FileStorageException("Only can upload jpg, png file", ExceptionEnum.INVALID_FILE_TYPE);
		}
    	
	}
	public void updateGameImage(MultipartFile file,Game game, User user) {
		if(!this.isImageFileTypeValid(file.getContentType())) {
			throw new FileStorageException("Only can upload jpg, png file", ExceptionEnum.INVALID_FILE_TYPE);
		}
    	if(game.getPublisher()!=user.getPublisher()) {
    		throw new ForbiddenException("You have no right to edit this game.", ExceptionEnum.ACCESS_DENIED);
    	}
    	
	}
}
