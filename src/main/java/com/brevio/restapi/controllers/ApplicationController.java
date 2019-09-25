package com.brevio.restapi.controllers;

import io.swagger.annotations.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brevio.restapi.dao.entitys.ClienteEntity;
import com.brevio.restapi.dao.repositorys.ClienteRepository;
import com.brevio.restapi.dao.repositorys.TransacoesRepository;
import com.brevio.restapi.models.BaseResponse;
import com.brevio.restapi.models.DepositoRequest;
import com.brevio.restapi.models.ExtornoRequest;
import com.brevio.restapi.models.PagarRequest;
import com.brevio.restapi.models.PagarResponse;
import com.brevio.restapi.models.SaldoRequest;
import com.brevio.restapi.models.SaldoResponse;
import com.brevio.restapi.services.ServicesVR;

@RestController
@RequestMapping("vr/v1")
@Api
public class ApplicationController {
	
	@Autowired
	private ClienteRepository client;
	@Autowired
	private TransacoesRepository transacao;
	
	private ServicesVR servicesVR = new ServicesVR();
	
	public ApplicationController(ClienteRepository client,
			TransacoesRepository transacao) {
		super();
		this.client = client;
		this.transacao = transacao;
	}
	
	public ApplicationController() {
		super();
	}
	@GetMapping
	public List<ClienteEntity> listaClientes(){
		List<ClienteEntity> list = (List<ClienteEntity>) client.findAll();
		return list;
	}
	
	@RequestMapping(value = "/saldo", method = RequestMethod.POST)
	public SaldoResponse saldo(@RequestBody SaldoRequest request){
		return servicesVR.getSaldo(request,client);
	}
	
	@PostMapping
	public PagarResponse pagar (@RequestBody PagarRequest request){
		return servicesVR.pagar(request, client, transacao);
	}
	
	@PutMapping
	public BaseResponse deposito(@RequestBody DepositoRequest request){
		return servicesVR.deposito(request, client, transacao);
	}
	
	@DeleteMapping
	public BaseResponse extorno(@RequestBody ExtornoRequest request){
		return servicesVR.extorno(request, client, transacao);
	}

}
