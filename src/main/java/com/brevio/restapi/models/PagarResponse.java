package com.brevio.restapi.models;

public class PagarResponse extends BaseResponse{

	private String autorizacaoPagamento;

	public String getAutorizacaoPagamento() {
		return autorizacaoPagamento;
	}

	public void setAutorizacaoPagamento(String autorizacaoPagamento) {
		this.autorizacaoPagamento = autorizacaoPagamento;
	}
}
