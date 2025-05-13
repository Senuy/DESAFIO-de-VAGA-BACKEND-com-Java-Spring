package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

	private RestTemplate restemplate; // Classe que opera chamadas http pelo  spring
	
	public void createTransaction(TransactionDTO transacation) throws Exception {
		
		User sender = this.userService.findUserById(transacation.senderId());
		User receiver = this.userService.findUserById(transacation.receiverId());

		userService.validateTransaction(sender, transacation.value());
		
		if()
	}
	
	public boolean authorizeTransaction(User sender,BigDecimal value) {
		ResponseEntity<Map> authorizationResponse = RestTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class)
		//44:02
		if(authorizationResponse.getStatusCode()==HttpStatus.OK) {
			
		}
	}
	

}
