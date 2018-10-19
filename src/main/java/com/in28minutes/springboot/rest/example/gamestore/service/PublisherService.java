package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherProfileResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.Role;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.repository.PublisherRepository;
import com.in28minutes.springboot.rest.example.gamestore.validator.PublisherValidation;

@Service
public class PublisherService {
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private GameService gameService;
	@Autowired
	private PublisherValidation publisherValidation;
	
	public List<Publisher> retrievePublishers(){
		return publisherRepository.findAll();
	}

	@LogActivity(activity="Register as publisher")
	@Transactional
	public Publisher createPublisher(UserPrincipal currentUser, PublisherRequest input) {
		Publisher publisher = new Publisher(input);
		User user = userService.retrieveUser(currentUser.getId());	
		publisherValidation.isUserAlreadyBecomeAPublisherValidator(user);
//		List<PublisherBankAccount> bankAccount = publisher.getBankAccount();
//		for(int i = 0; i<bankAccount.size();i++) {
//			Bank bank = bankService.retrieveBank(bankAccount.get(i).getBank().getId());
//			bankAccount.get(i).setBank(bank);
//		}
		publisher.setSellingBalance((float) 0);
		Publisher savedPublisher = publisherRepository.save(publisher);
		Set<Role> roles = user.getRoles();
		Role pubRole = roleService.RetrieveRole(2);
		roles.add(pubRole);
		user.setRoles(roles);
		user.setPublisher(savedPublisher);
		userService.UpdateUser(user);
		return savedPublisher;
	}
	public Publisher retrievePublisher(Long id) {
		Optional<Publisher> publisher = publisherRepository.findById(id);
		if(!publisher.isPresent())
			throw new ClassNotFoundException("Publisher with id : "+id+" doesn't exist",ExceptionEnum.CLASS_NOT_FOUND);
		return publisher.get();
	}
//	public Publisher retrievePublisherByUser(User user) {
//		Optional<Publisher> publisher = publisherRepository.findByUser(user);
//		if(!publisher.isPresent())
//			throw new NotFoundException("Publisher Not FOund");
//		return publisher.get();
//	}
//	@PublisherChangeRecorder(activity="Update Profile")
	public void setBalance(Publisher publisher) {
		publisherRepository.setBalance(publisher.getId(), publisher.getSellingBalance());
	}
	public PublisherProfileResponse retrieveMyProfile(UserPrincipal currentUser) {
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		if(publisher==null) {
			throw new ForbiddenException("You are not registered as publisher");
		}
		List<GameResponse> game = gameService.retrieveGamesByPublisher(publisher.getId());
		PublisherProfileResponse result = new PublisherProfileResponse(publisher, user.getName(), game);
		return result;
	}

	@LogActivity(activity="Update Publisher Profile")
	public Publisher updatePublisher(UserPrincipal currentUser, PublisherRequest input) {
		User user = userService.retrieveUser(currentUser.getId());
		Publisher publisher = user.getPublisher();
		publisher.setPublisherName(input.getPublisherName());
		return publisherRepository.save(publisher);
	}
}
