package com.picpaysimplificado.domain.user;

import java.math.BigDecimal;

import com.picpaysimplificado.dtos.UserDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor // Gera Construtores
@EqualsAndHashCode(of = "id") // Chave primária tabela
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Geracao do ID incremental
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String document;
	
	@Column(unique = true)
	private String email;
	
	private String passWord;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING) // Para representar algum dos valores do enum
	private UserType userType;
	

	public  User(UserDTO data) {
		this.firstName = data.firstName();
		this.lastName = data.lastName();
		this.balance = data.balance();
		this.userType = data.usertype();
		this.passWord = data.password();
		this.email = data.password();
	}


	public UserType getUserType() { // por algum motivo o Get do lombok nao funfa
		return userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFistName() {
		return firstName ;
	}

	public void setName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public  void  User(UserDTO data) {
		this.firstName = data.firstName();
		this.lastName = data.lastName();
		this.balance = data.balance();
		this.userType = data.usertype();
		/*
		 * this.document = data.document();
		 * this.email = data.email();
		 * this.passWord = data.password();
		 */
	}
	
}
