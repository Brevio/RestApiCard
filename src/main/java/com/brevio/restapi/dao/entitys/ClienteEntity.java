package com.brevio.restapi.dao.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CLIENTE")
public class ClienteEntity {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long card;
	
	@Column
	private String pass_card;
	
	@Column
	private Double balance_card;

	public Long getCard() {
		return card;
	}

	public void setCard(Long card) {
		this.card = card;
	}

	public String getPass_card() {
		return pass_card;
	}

	public void setPass_card(String pass_card) {
		this.pass_card = pass_card;
	}

	public Double getBalance_card() {
		return balance_card;
	}

	public void setBalance_card(Double balance_card) {
		this.balance_card = balance_card;
	}
	
	

}
