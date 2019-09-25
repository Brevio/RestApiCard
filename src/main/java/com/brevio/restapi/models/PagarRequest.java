package com.brevio.restapi.models;

public class PagarRequest extends BaseRequest{
	
	private String senha;
	private double valor;
	

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
