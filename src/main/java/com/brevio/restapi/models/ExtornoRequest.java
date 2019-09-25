package com.brevio.restapi.models;

public class ExtornoRequest extends BaseRequest{
	
	private String codAutorizacaoPagamento;

	public String getCodAutorizacaoPagamento() {
		return codAutorizacaoPagamento;
	}

	public void setCodAutorizacaoPagamento(String codAutorizacaoPagamento) {
		this.codAutorizacaoPagamento = codAutorizacaoPagamento;
	}

}
