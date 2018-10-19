package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.EditUserProfileRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.LoginRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.SignupRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UploadFileResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserProfileResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.Role;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.UserRepository;
import com.in28minutes.springboot.rest.example.gamestore.security.JwtTokenProvider;
import com.in28minutes.springboot.rest.example.gamestore.util.FileUtil;
import com.in28minutes.springboot.rest.example.gamestore.validator.FileUploadValidation;
import com.in28minutes.springboot.rest.example.gamestore.validator.UserValidation;

@Service
public class UserService implements UserDetailsService {
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserValidation userValidation;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private FileUploadValidation fileUploadValidation;
	
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	public User getUserByUsername (String username) {
		Optional<User> user = userRepository.getByUsername(username);
		if (!user.isPresent()) {
			throw new ClassNotFoundException("Username : "+ username);
		}
		return user.get();
	}
	
	public User retrieveUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new ClassNotFoundException("id : "+ id);
		}
		return user.get();
	}
	public UserProfileResponse myProfile( UserPrincipal currentUser) {
		User user = this.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		List<Game> games = new ArrayList<Game>();
		games.addAll(user.getGameOwned());
		Set<GameResponse> gamesOwned = new HashSet<GameResponse>();
		for(int i = 0; i<games.size();i++) {
			GameResponse game = new GameResponse(games.get(i));
			gamesOwned.add(game);
		}
		UserProfileResponse userProfile = new UserProfileResponse(user, publisher, gamesOwned);
		return userProfile;
	}
	public User createUser (SignupRequest input){
		User user = new User(input);
		userValidation.signUpValidator(user);
		Role role = roleService.RetrieveRole(1);
		Set<Role> roles = user.getRoles();
		roles.add(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(roles);
		user.setDefault();
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
	public void setBalance(User user) {
		userRepository.setBalance(user.getId(), user.getBalance());
	}
	
	public void UpdateUser(User user) {
		userRepository.save(user);
	}
	
	@Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws ClassNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> 
                        new ClassNotFoundException("User with username or email : " + usernameOrEmail + "doesn't exist", ExceptionEnum.CLASS_NOT_FOUND)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ClassNotFoundException("User with id : "+ id+" doesn't exist", ExceptionEnum.CLASS_NOT_FOUND)
        );

        return UserPrincipal.create(user);
    }
    @LogActivity(activity="Login", isAuthenticated=false)
    public String signin(LoginRequest loginRequest) {
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return jwt;
    }
    public User retrieveUserByPublisher(Publisher publisher) {
    	Optional<User> user = userRepository.findByPublisher(publisher);
    	if(!user.isPresent()) {
    		throw new ClassNotFoundException("publisher id : "+ publisher.getId());
    	}
    	return user.get();
    }

 	@LogActivity(activity="Edit Profile")
    public User updateProfile( UserPrincipal currentUser, EditUserProfileRequest input) {
    	User user = this.retrieveUser(currentUser.getId());
    	user.setEditedProfile(input);
    	userValidation.signUpAgeValidator(user);
		User savedUser = userRepository.save(user);
		return savedUser;
    }
 	@LogActivity(activity="Edit Photo Profile")
    public UploadFileResponse updatePhotoProfile( UserPrincipal currentUser, MultipartFile file){
    	User user = retrieveUser(currentUser.getId());
    	String contentType = file.getContentType();
    	fileUploadValidation.updatePhotoProfileValidation(file);
    	String fileName = "user/user-"+user.getId() + "."+FileUtil.getFileExtension(file);
    	fileName = fileStorageService.storeFile(file, fileName);
    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(fileName)
                .toUriString();
    	user.setPhotoProfile(fileDownloadUri);
    	userRepository.save(user);
    	
        return new UploadFileResponse(fileName, fileDownloadUri,contentType, file.getSize());
    }
 	public String testEncode(String password) {
 		return passwordEncoder.encode(password);
 	}
 	public boolean checkPassword(UserPrincipal currentUser, String password) {
 		User user = this.retrieveUser(currentUser.getId());
 		return passwordEncoder.matches(password, user.getPassword());
 	}
}
