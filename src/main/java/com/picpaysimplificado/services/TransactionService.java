package com.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
	
	
	private final  UserService userService;
	
	
	private final TransactionRepository repository;
	
	
	public void createTransaction(TransactionDTO transacation) throws Exception {
		
		User sender = this.userService.findUserById(transacation.senderId());
		User receiver = this.userService.findUserById(transacation.receiverId());

		userService.validateTransaction(sender, transacation.value());
	}

}
