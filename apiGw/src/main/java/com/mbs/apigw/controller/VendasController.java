package com.mbs.apigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbs.apigw.comunicacao.ClienteRoteamento;
import com.mbs.apigw.comunicacao.VendasRoteamento;
import entidades.Venda;

public class VendasController {
	
	@Autowired
	private VendasRoteamento vendasRoteamento;
	
	@Autowired
	private ClienteRoteamento clienteRoteamento;
	
	
	@RequestMapping(value = "/v1/api-gw/venda",
			method = RequestMethod.POST)
	public ResponseEntity<String> salvarVenda(@RequestBody Venda venda){
		return vendasRoteamento.salvarVenda(venda);
	}
	
	//Orquestramento
	@RequestMapping(value = "/v1/api-gw/processarVenda",
			method = RequestMethod.POST)
	public ResponseEntity<String> processarVenda(@RequestBody Venda venda){
		
		System.out.println("executando processsamento venda");
		ResponseEntity<Boolean> existeCliente = 
				clienteRoteamento.existeCliente(venda.getCodCliente());
		
		if(existeCliente.getBody().equals(Boolean.TRUE)) {
			System.out.println("cliente nao existe");
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("cliente nao existe");
		}
		
		System.out.println("cliente existe");		
		System.out.println("Processando venda");	
		
		ResponseEntity<String> salvarVenda = vendasRoteamento.salvarVenda(venda);
		
		if(salvarVenda.getStatusCode() ==  HttpStatus.BAD_REQUEST) {
			System.out.println("Erro ao Salvar A venda");
			ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body("Erro ao salvar a avenda");
		}
		
		System.out.println("Salvar venda Realizada com sucesso");	
		
		//TODO Ajustar para a chamada correta broker
		System.out.println("Mockando o envio de Mensagem");
		
		return null;
		
		
	}
	
	
}
