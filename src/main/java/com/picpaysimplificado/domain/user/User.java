package com.picpaysimplificado.domain.user;

import java.math.BigDecimal;

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
	
	private String name;
	
	private String lastName;
	
	@Column(unique = true)
	private String documente;
	
	@Column(unique = true)
	private String email;
	
	private String passWord;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING) // Para representar algum dos valores do enum
	private UserType userType;
}
