package com.brevio.restapi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brevio.restapi.dao.entitys.ClienteEntity;
import com.brevio.restapi.dao.entitys.TransacoesEntity;
import com.brevio.restapi.dao.repositorys.ClienteRepository;
import com.brevio.restapi.dao.repositorys.TransacoesRepository;
import com.brevio.restapi.models.BaseResponse;
import com.brevio.restapi.models.DepositoRequest;
import com.brevio.restapi.models.ExtornoRequest;
import com.brevio.restapi.models.PagarRequest;
import com.brevio.restapi.models.PagarResponse;
import com.brevio.restapi.models.SaldoRequest;
import com.brevio.restapi.models.SaldoResponse;
import com.brevio.restapi.utils.Constantes;

@Service
public class ServicesVR {

	public SaldoResponse getSaldo(SaldoRequest request, ClienteRepository cliente) {
		SaldoResponse response = new SaldoResponse();
		Optional<ClienteEntity> findById = cliente.findById(request.getNumCard());
		if(findById.isPresent()){
			ClienteEntity c = findById.get();
			if(c.getPass_card().equals(request.getSenha())){
				response.setStatus(Constantes.SUCCESS_COD);
				response.setMessage(Constantes.SUCCESS);
				response.setSaldo(c.getBalance_card());
			} else {
				response.setStatus(Constantes.BAD_REQUEST);
				response.setMessage(Constantes.BAD_PASS);
			}
		} else {
			response.setStatus(Constantes.BAD_REQUEST);
			response.setMessage(Constantes.INVALID_CARD);
		}
		return response;
	}

	public PagarResponse pagar(PagarRequest request, ClienteRepository cliente, TransacoesRepository transacao) {
		PagarResponse response = new PagarResponse();
		Optional<ClienteEntity> findById = cliente.findById(request.getNumCard());
		if(findById.isPresent()){
			ClienteEntity c = findById.get();
			if(c.getPass_card().equals(request.getSenha())){
				if(c.getBalance_card() >= request.getValor()){
					TransacoesEntity t = new TransacoesEntity();
					String id = new java.sql.Timestamp(System.currentTimeMillis()).toString();
					t.setNum_card(request.getNumCard());
					t.setType(Constantes.OUT);
					t.setValue(request.getValor());
					t.setId(id);
					transacao.save(t);
					c.setBalance_card(c.getBalance_card() - request.getValor());
					cliente.save(c);
					response.setStatus(Constantes.CREATED_COD);
					response.setMessage(Constantes.CREATED_TRASACTION);
					response.setAutorizacaoPagamento(id);
				} else {
					response.setStatus(Constantes.BAD_REQUEST);
					response.setMessage(Constantes.INSUFFICIENT_FUNDS);
				}
			} else {
				response.setStatus(Constantes.BAD_REQUEST);
				response.setMessage(Constantes.BAD_PASS);
			}
		} else {
			response.setStatus(Constantes.BAD_REQUEST);
			response.setMessage(Constantes.INVALID_CARD);
		}
		return response;
	}

	public BaseResponse deposito(DepositoRequest request, ClienteRepository cliente, TransacoesRepository transacao) {
		BaseResponse response = new BaseResponse();
		Optional<ClienteEntity> findById = cliente.findById(request.getNumCard());
		if(findById.isPresent()){
			ClienteEntity c = findById.get();
			TransacoesEntity t = new TransacoesEntity();
			String id = new java.sql.Timestamp(System.currentTimeMillis()).toString();
			t.setNum_card(request.getNumCard());
			t.setType(Constantes.IN);
			t.setValue(request.getValor());
			t.setId(id);
			transacao.save(t);
			c.setBalance_card(c.getBalance_card() + request.getValor());
			cliente.save(c);
			response.setStatus(Constantes.CREATED_COD);
			response.setMessage(Constantes.CREATED_TRASACTION);
		} else {
			response.setStatus(Constantes.BAD_REQUEST);
			response.setMessage(Constantes.INVALID_CARD);
		}
		return response;
	}

	public BaseResponse extorno(ExtornoRequest request, ClienteRepository cliente, TransacoesRepository transacao) {
		BaseResponse response = new BaseResponse();
		Optional<TransacoesEntity> findById = transacao.findById(request.getCodAutorizacaoPagamento());
		if(findById.isPresent()){
			TransacoesEntity t = findById.get();
			double extorno = transacao.findById(request.getCodAutorizacaoPagamento()).get().getValue();
			transacao.delete(t);		
			ClienteEntity c = cliente.findById(request.getNumCard()).get();
			c .setBalance_card(c.getBalance_card() + extorno);
			cliente.save(c);
			response.setStatus(Constantes.SUCCESS_COD);
			response.setMessage(Constantes.CREATED_TRASACTION);
		} else {
			response.setStatus(Constantes.BAD_REQUEST);
			response.setMessage(Constantes.INVALID_COD);
		}
		return response;
	}	
}
