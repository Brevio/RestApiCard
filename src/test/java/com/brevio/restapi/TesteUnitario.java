package com.brevio.restapi;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.brevio.restapi.controllers.ApplicationController;
import com.brevio.restapi.controllers.RestApiApplication;
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
import com.brevio.restapi.utils.Constantes;
import com.brevio.services.mock.PontosFidelidades;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiApplication.class)
public class TesteUnitario {
	
	Logger logger = LoggerFactory.getLogger(TesteUnitario.class);
	
	@Autowired
	private ClienteRepository dbCliente;
	
	@Autowired
	private TransacoesRepository dbTransacoes;
	
	@Mock
	private RestTemplate restTemplate;
	
	private ApplicationController controller;
	
	private long numCard;
	
	private String transaction;
	
	private PontosFidelidades pontos;
	
	@SuppressWarnings("unused")
	private TesteIntegradoMock apiExt;
	
	@Before
	public void init(){
		controller = new ApplicationController(dbCliente, dbTransacoes);
		numCard = 1234567891011123L;
		apiExt = new TesteIntegradoMock(restTemplate);
	}
	
	
	@Test
	public void acessoApiExt() {
		logger.info("Teste acessoApiExt");
		String[] cards = {"1234567891011124","1234567891011123","1234567891011125"};

		for(int i = 0; i < cards.length; i++) {
			pontos = restTemplate.getForEntity("https://livelo.com.br/points/"+ cards[i],PontosFidelidades.class).getBody();
			logger.info("Cartão {} contém {} pontos",cards[i], pontos.getPoints());
		}
	}
	
	/**
	 * Teste realizar um pagamento de valor aleatorio em todos os cartões da base.
	 * Caso tenha mais de 7000 pontos aplica-se desconto de 10%
	 */
	
	@Test
	public void pagarComDesconto() {
		List<ClienteEntity> cards = controller.listaClientes();
		PagarRequest request =  new PagarRequest();
		
		for(ClienteEntity cli : cards) {
			double valor = Math.random() * 1000;
			logger.info("Cartão {}", cli.getCard());
			ResponseEntity<PontosFidelidades> responseEntity =
					restTemplate.getForEntity("https://livelo.com.br/points/"+ String.valueOf(cli.getCard()),PontosFidelidades.class);
			if(responseEntity.getStatusCode() == HttpStatus.OK){
				pontos = responseEntity.getBody();
				if(pontos.getPoints() >= 7000L) {
					logger.info("Desconto aplicado"); 
					valor =valor - (valor / 100 * 10);
					request.setNumCard(cli.getCard());
					request.setSenha(cli.getPass_card());
					request.setValor(valor);
					PagarResponse response = controller.pagar(request);	
					logger.info("Pagemento no valor {}, enviado. Status: {}", valor, response.getMessage());
				} else {
					logger.info("Desconto não aplicado");
					request.setNumCard(cli.getCard());
					request.setSenha(cli.getPass_card());
					request.setValor(valor);
					PagarResponse response = controller.pagar(request);	
					logger.info("Pagemento no valor {}, enviado. Status: {}", valor, response.getMessage());
				}
			}
		}
	}
	
	@Test
	public void list(){
		List<ClienteEntity> response = controller.listaClientes();
		assert(response != null);
		
	}
	
	@Test
	public void saldo(){	
		SaldoRequest request =  new SaldoRequest();
		request.setNumCard(numCard);
		request.setSenha("010203");	
		SaldoResponse saldoResponse = controller.saldo(request);
		assertEquals(Constantes.SUCCESS_COD, saldoResponse.getStatus());
	}
	
	@Test
	public void pagar(){
		PagarRequest request =  new PagarRequest();
		request.setNumCard(numCard);
		request.setSenha("010203");
		request.setValor(10.13);
		PagarResponse response = controller.pagar(request);
		transaction = response.getAutorizacaoPagamento();
		assertEquals(Constantes.CREATED_COD, response.getStatus());
	}
	
	@Test 
	public void deposito(){ 
		DepositoRequest request =  new DepositoRequest();
		request.setNumCard(numCard);
		request.setValor(400.00);
		BaseResponse response = controller.deposito(request); 
		assertEquals(Constantes.CREATED_COD, response.getStatus());
	}
	
	@After(value="pagar")
	public void extorno(){
		ExtornoRequest request =  new ExtornoRequest();
		request.setNumCard(numCard);
		request.setCodAutorizacaoPagamento(transaction);
		BaseResponse response = controller.extorno(request); 
		assertEquals(Constantes.SUCCESS_COD, response.getStatus());
	}

}
