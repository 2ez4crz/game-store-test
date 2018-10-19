package com.in28minutes.springboot.rest.example.gamestore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springboot.rest.example.gamestore.aop.LogActivity;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.TransactionDetailResponse;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserPrincipal;
import com.in28minutes.springboot.rest.example.gamestore.contract.UserResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;
import com.in28minutes.springboot.rest.example.gamestore.entity.Transaction;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionGamePurchase;
import com.in28minutes.springboot.rest.example.gamestore.entity.TransactionTopUp;
import com.in28minutes.springboot.rest.example.gamestore.entity.User;
import com.in28minutes.springboot.rest.example.gamestore.entity.Voucher;
import com.in28minutes.springboot.rest.example.gamestore.entity.VoucherType;
import com.in28minutes.springboot.rest.example.gamestore.enumeration.TransactionType;
import com.in28minutes.springboot.rest.example.gamestore.exception.ExceptionEnum;
import com.in28minutes.springboot.rest.example.gamestore.exception.ForbiddenException;
import com.in28minutes.springboot.rest.example.gamestore.exception.ClassNotFoundException;
import com.in28minutes.springboot.rest.example.gamestore.exception.TransactionRejectedException;
import com.in28minutes.springboot.rest.example.gamestore.repository.TransactionRepository;
import com.in28minutes.springboot.rest.example.gamestore.validator.TransactionValidation;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionValidation transactionValidation;
	@Autowired
	private UserService userService;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private TransactionTopUpService topUpService;
	@Autowired
	private GameService gameService;
	@Autowired
	private TransactionGamePurchaseService gamePurchaseService;
	@Autowired
	private PublisherService publisherService;
	@Autowired 
	private RoleService roleService;
	

	@LogActivity(activity="Top Up Balance")
	@Transactional
	public TransactionTopUp doTransactionTopUp(UserPrincipal currentUser, String input) {
		
		User user = userService.retrieveUser(currentUser.getId());
		Voucher voucher =  voucherService.RetrieveVoucher(input);
		if(!voucher.isActive()) {
			throw new TransactionRejectedException("Voucher Code has been used.", ExceptionEnum.VOUCHER_ALREADY_USED);
		}
		VoucherType voucherType = voucher.getVoucherType();
		
		String invoice = setInvoice("01",topUpService.getNextId());
		Transaction transaction = new Transaction(invoice, user, voucherType.getValue(), 0, TransactionType.Top_Up);
		Transaction savedTransaction = transactionRepository.save(transaction);
		TransactionTopUp topUp = new TransactionTopUp(savedTransaction, voucher);
		
		TransactionTopUp savedTopUp = topUpService.doTopUp(topUp);

		float newBalance = user.getBalance() + transaction.getCredit();
		user.setBalance(newBalance);
		userService.setBalance(user);
		
		voucherService.setDeactive(savedTopUp.getVoucher().getCode());
		
		return savedTopUp;
	}

	@LogActivity(activity="Purchase a game")
	@Transactional
	public TransactionGamePurchase doGamePurchase(UserPrincipal currentUser, Long gameId) {
		
		User user = userService.retrieveUser(currentUser.getId());
		Game game = gameService.retrieveGame(gameId);
		Publisher publisher = game.getPublisher();
//		Rating rating = game.getRatingCode();
		transactionValidation.gamePurchaseValidation(game, user);
		float newBalance = user.getBalance() - game.getPrice();
		String invoice = this.setInvoice("02", gamePurchaseService.getNextId());
		Transaction transaction = new Transaction(invoice, user, 0, game.getPrice(), TransactionType.Game_Purchase);
		Transaction savedTransaction = transactionRepository.save(transaction);
		
		TransactionGamePurchase gamePurchase = new TransactionGamePurchase();
		gamePurchase.setGame(game);
		gamePurchase.setTransaction(savedTransaction);
		
		TransactionGamePurchase savedGamePurchase = gamePurchaseService.doGamePurchase(gamePurchase);

		user.setBalance(newBalance);
		user.getGameOwned().add(game);
		userService.setBalance(user);
		
		float publisherBalance = (float) (publisher.getSellingBalance() + (game.getPrice()*0.9));
		
		publisher.setSellingBalance(publisherBalance);
		publisherService.setBalance(publisher);
		
		Long gamePurchased = game.getPurchased() +1;

		game.setPurchased(gamePurchased);
		gameService.setPurchased(game);
		
		return savedGamePurchase;
	}
	
	public String setInvoice(String  type, Long id) {
		Date date = new Date();
		String result = "INV-"+type +"-"+ date.getMonth() + date.getDate() +  id;
		return result;
		
	}
	
	public List<TransactionDetailResponse> transactionHistory( UserPrincipal currentUser) {
		User user = userService.retrieveUser(currentUser.getId());
		List<Transaction> transaction =  transactionRepository.findAllByUser(user);
		List<TransactionDetailResponse> result = new ArrayList<TransactionDetailResponse>();
		UserResponse userResponse = new UserResponse(user);
		for(int i = 0; i<transaction.size(); i++) {
			TransactionDetailResponse transactiondetail = new TransactionDetailResponse(transaction.get(i), userResponse);
			result.add(transactiondetail);
		}
		return result;
	}
	public Transaction retirieveTransaction(String invoice) {
		Optional<Transaction> transaction = transactionRepository.findById(invoice);
		if(!transaction.isPresent()) {
			throw new ClassNotFoundException("Transaction with Invoice "+invoice+" does not exist.",ExceptionEnum.CLASS_NOT_FOUND);
		}
		return transaction.get();
	}
	public TransactionDetailResponse RetrieveTransactionDetail(String invoice,  UserPrincipal currentUser) {
		User user = userService.retrieveUser(currentUser.getId());
		Transaction transaction = this.retirieveTransaction(invoice);
		if(user!=transaction.getUser()&&!user.getRoles().contains(roleService.RetrieveRole(3))) {
			throw new ForbiddenException("Forbidden");
		}
		UserResponse userTransaction = new UserResponse(transaction.getUser());
		TransactionDetailResponse response = new TransactionDetailResponse(transaction, userTransaction);
		if(transaction.getTransactionType().equals(TransactionType.Top_Up)) {
			TransactionTopUp topUp = topUpService.retrieveTopUpByTransaction(transaction);
			response.setDetail(topUp.getVoucher());
		}else if(transaction.getTransactionType().equals(TransactionType.Game_Purchase))
		{
			TransactionGamePurchase gamePurchase = gamePurchaseService.retrieveGamePurchaseByTransaction(transaction);
			GameResponse game = new GameResponse(gamePurchase.getGame());
			response.setDetail(game);
		}
		return response;
	}
}
