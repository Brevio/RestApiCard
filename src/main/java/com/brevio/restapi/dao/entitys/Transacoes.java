package com.brevio.restapi.dao.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACAO_TABLE")
public class Transacoes {

	@Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
	@Column(name = "NUM_CARD", nullable = true, length = 14) 
	private long num_card;

	@Column(name = "VALUE", nullable = true, length = 255) 
	private double value;
	
	// 0 - saida, 1- entrada 
	@Column(name = "TYPE", nullable = true, length = 10)
	private int type;


	public long getNum_card() {
		return num_card;
	}

	public void setNum_card(long num_card) {
		this.num_card = num_card;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
