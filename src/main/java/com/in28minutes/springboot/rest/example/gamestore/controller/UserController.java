package com.in28minutes.springboot.rest.example.gamestore.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.contract.EditUserProfileRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UploadFileResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserProfileResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.security.CurrentUser;
import com.in28minutes.springboot.rest.example.gamestore.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> retrieveAllUsers(){
		return userService.retrieveAllUsers();
	}
	@GetMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User retrieveUser(@PathVariable long id) {
		User user = userService.retrieveUser(id);
		return user;
	}
	@GetMapping("username/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Object retrieveUser(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
//	@PostMapping("/registration")
//	public Object createUsers(@RequestBody User user){
//		User savedUser = userService.createUser(user);
//		BaseResult result = new BaseResult(savedUser);
//		return result;
//	}
	
 	@GetMapping("myProfile")
    public UserProfileResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
 		return userService.myProfile(currentUser);
    }
 	
 	@PutMapping("myProfile/edit")
 	public ResponseEntity<Object> updateProfile(@CurrentUser UserPrincipal currentUser, @RequestBody EditUserProfileRequest  user){
 		User updatedUser = userService.updateProfile(currentUser, user);
// 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/myProfile/edit")
//				.buildAndExpand(updatedUser.getId()).toUri();
		return ResponseEntity.ok().body("Update Success!");
 	}
// 	@PostMapping("myProfile/updatePhoto")
// 	@Consumes({ MediaType.MULTIPART_FORM_DATA })
// 	public String updatePhoto(
// 			@FormDataParam("file") final InputStream inputStream,
// 			@FormDataParam("file") final FormDataContentDisposition fileDetails, 
// 			@CurrentUser UserPrincipal currentUser) throws IOException{
// 		return userService.updatePhotoProfile(inputStream, fileDetails, currentUser);
// 		
// 	}
 	@PostMapping("/myProfile/edit/photoProfile")
    public UploadFileResponse uploadFile(@CurrentUser UserPrincipal currentUser, @RequestParam("file") MultipartFile file) {
        return userService.updatePhotoProfile(currentUser, file);
    }
 	@GetMapping("/check/{password}")
    public Boolean checkPass(@CurrentUser UserPrincipal currentUser, @PathVariable String password) {
		return userService.checkPassword(currentUser, password);
    }
}
