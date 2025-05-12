package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
	
	
	private UserService userService;
	
	
	private TransactionRepository repository;

	private RestTemplate restemplate;
	
	public void createTransaction(TransactionDTO transacation) throws Exception {
		
		User sender = this.userService.findUserById(transacation.senderId());
		User receiver = this.userService.findUserById(transacation.receiverId());

		userService.validateTransaction(sender, transacation.value());
		
		if()
	}
	
	public boolean authorizeTransaction(User sender,BigDecimal value) {
		restemplate.getForEntity(null, null)
		
	}
	

}
