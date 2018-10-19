package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.ImagePathResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UploadFileResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameGallery;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.mapper.GameGalleryMapper;
import com.in28minutes.springboot.rest.example.gamestore.repository.GameGalleryRepository;
import com.in28minutes.springboot.rest.example.gamestore.util.FileUtil;
import com.in28minutes.springboot.rest.example.gamestore.validator.FileUploadValidation;

@Service
public class GameGalleryService {
	@Autowired
	private GameGalleryRepository gameGalleryRepository;
	@Autowired
	private UserService userService;
	@Autowired
    private FileStorageService fileStorageService;
	@Autowired
	private FileUploadValidation fileUploadValidation;
	@Autowired
	private GameService gameService;
	
	
	public UploadFileResponse addImages(UserPrincipal currentUser, Long id,MultipartFile file){
		User user = userService.retrieveUser(currentUser.getId());
		Game game = gameService.retrieveGame(id);
		fileUploadValidation.updateGameImage(file, game, user);
    	String contentType = file.getContentType();
    	Long nextId = this.getNextId();
    	String fileName = "image"+game.getId()+"-"+nextId+"."+FileUtil.getFileExtension(file);
    	fileStorageService.storeFile(file, "game/"+fileName);
    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    			.path("/images/game/")
                .path(fileName)
                .toUriString();
    	GameGallery gameGallery = new GameGallery(nextId, game, fileDownloadUri);
    	gameGalleryRepository.save(gameGallery);
        return new UploadFileResponse(fileName, fileDownloadUri,contentType, file.getSize());
	}
	public Long getNextId() {
		return gameGalleryRepository.getNextId()+1;
	}
	public List<ImagePathResponse> retrieveAllImageByGame(Long gameId){
		Game game = gameService.retrieveGame(gameId);
		List<GameGallery> gameGallery = gameGalleryRepository.findAllByGame(game);
		return GameGalleryMapper.ListGameGalleryTOListImagePathResponse(gameGallery);
	}
	public GameGallery retrieveGameGallery(Long id) {
		Optional<GameGallery> gameGallery = gameGalleryRepository.findById(id);
		if(!gameGallery.isPresent()) {
			throw new ClassNotFoundException("GameGallery Not Found.");
		}
		return gameGallery.get();
	}
	
	public void deleteGameGallery(Long id) {
		GameGallery gameGallery = this.retrieveGameGallery(id);
		gameGalleryRepository.delete(gameGallery);
	}
	@LogActivity(activity="Upload images into game gallery")
	public List<UploadFileResponse> uploadMultipleFiles(UserPrincipal currentUser, Long id, MultipartFile[] files) {
		List<UploadFileResponse> result =  Arrays.asList(files)
				.stream()
                .map(file -> addImages(currentUser, id, file))
                .collect(Collectors.toList());
		return result;
	}
}
