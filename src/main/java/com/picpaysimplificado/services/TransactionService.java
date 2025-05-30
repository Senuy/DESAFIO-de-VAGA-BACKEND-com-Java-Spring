package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private UserService userService;

	private TransactionRepository repository;

	private RestTemplate restTemplate; // Classe que opera chamadas http pelo spring

	public void createTransaction(TransactionDTO transaction) throws Exception {

		User sender = this.userService.findUserById(transaction.senderId());
		User receiver = this.userService.findUserById(transaction.receiverId());

		userService.validateTransaction(sender, transaction.value());

		boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());

		if (!isAuthorized) {
			throw new Exception("Transação nao autorizada");
		}

		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());

		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		this.repository.save(newTransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
	}

	public boolean authorizeTransaction(User sender, BigDecimal value) {
		ResponseEntity<Map> authorizationReponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize",
				Map.class);

		if (authorizationReponse.getStatusCode() == HttpStatus.OK) {
			String message = (String) authorizationReponse.getBody().get("status");
			return "success".equalsIgnoreCase(message);

		} else
			return false;
	}

}
