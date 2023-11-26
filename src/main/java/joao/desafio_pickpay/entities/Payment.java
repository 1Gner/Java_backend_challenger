package joao.desafio_pickpay.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_pagamentos")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	@ManyToOne
	@JoinColumn(name = "Pay_comun_id")
	private User user1;
	
	@ManyToOne
	@JoinColumn(name = "Pay_merchant_id")
	private User user2;
	
	private Double valor;
	

	private LocalDateTime transactionTime;
	
	
	public Payment() {
		
	}


	public Payment(Long id, User user1, User user2, Double valor, LocalDateTime transactionTime) {
		super();
		Id = id;
		this.user1 = user1;
		this.user2 = user2;
		this.valor = valor;
		this.transactionTime = transactionTime;
	}






	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	

	public User getUser1() {
		return user1;
	}


	public void setUser1(User user1) {
		this.user1 = user1;
	}


	public User getUser2() {
		return user2;
	}


	public void setUser2(User user2) {
		this.user2 = user2;
	}


	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(Id, other.Id);
	}

	
	
	
	
	
}
