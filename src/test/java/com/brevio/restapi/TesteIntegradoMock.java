package com.brevio.restapi;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.brevio.services.mock.PontosFidelidades;


public class TesteIntegradoMock {
	Logger logger = LoggerFactory.getLogger(TesteIntegradoMock.class);
	
	
	public TesteIntegradoMock(RestTemplate restTemplate) {
		PontosFidelidades mock = new PontosFidelidades("234567891011123", 8000L);
		Mockito
			.when(restTemplate.getForEntity("https://livelo.com.br/points/1234567891011123", PontosFidelidades.class))
			.thenReturn(new ResponseEntity<PontosFidelidades>(mock , HttpStatus.OK));

		mock = new PontosFidelidades("1234567891011124", 6000L);
		Mockito
			.when(restTemplate.getForEntity("https://livelo.com.br/points/1234567891011124", PontosFidelidades.class))
			.thenReturn(new ResponseEntity<PontosFidelidades>(mock , HttpStatus.OK));
		
		mock = new PontosFidelidades("1234567891011125", 6300L);
		Mockito
			.when(restTemplate.getForEntity("https://livelo.com.br/points/1234567891011125", PontosFidelidades.class))
			.thenReturn(new ResponseEntity<PontosFidelidades>(mock , HttpStatus.OK));
	}
}