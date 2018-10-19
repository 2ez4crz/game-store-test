package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.in28minutes.springboot.rest.example.gamestore.contract.ImagePathResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UploadFileResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.GameGalleryService;

@RestController
@RequestMapping("/api")
public class GameGalleryController {
	@Autowired
	GameGalleryService gameGalleryService;
	
//	@PostMapping("game/{id}/gallery/add")
//	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
//	public  UploadFileResponse uploadImages(@CurrentUser UserPrincipal currentUser, @PathVariable Long id, @RequestParam("file") MultipartFile file) {
//        return gameGalleryService.addImages(currentUser, id, file);
//    }
	@PostMapping("game/{id}/gallery/add")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public List<UploadFileResponse> uploadMultipleFiles(@CurrentUser UserPrincipal currentUser, @PathVariable Long id, @RequestParam("files") MultipartFile[] files) {
		List<UploadFileResponse> result =  gameGalleryService.uploadMultipleFiles(currentUser, id, files);
		return result;
    }
	@GetMapping("game/{id}/gallery")
	public List<ImagePathResponse> retrieveAllByGame(@PathVariable Long id){
		return gameGalleryService.retrieveAllImageByGame(id);
	}
	@DeleteMapping("game/gallery/{id}/delete")
	@PreAuthorize("hasRole('ROLE_PUBLISHER')")
	public ResponseEntity<Object> deleteImage(@PathVariable Long id){
		gameGalleryService.deleteGameGallery(id);
		return ResponseEntity.ok().body("Image Deleted Successfully!");
	}
}
