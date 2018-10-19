package com.in28minutes.springboot.rest.example.gamestore.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	public static String getFileExtension(MultipartFile file) {
		return FilenameUtils.getExtension(file.getOriginalFilename());
	}
}
