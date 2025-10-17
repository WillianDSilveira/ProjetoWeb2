package com.mbs.apigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbs.apigw.comunicacao.ClienteRoteamento;
import com.mbs.clienteServices.entidades.Cliente;




public class ClienteController {	
	
	@Autowired
	private ClienteRoteamento clienteRoteamento;		
	
	@RequestMapping(value = "/v1/api-gw/cliente/existe-cliente/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<Boolean> existeCliente(@PathVariable Integer id) {
		
		System.out.println("Executando Existe Cliente");
		System.out.println("Chamando ClienteService: endpoint: existe-cliente");
		
		//chamando o cliente service
		ResponseEntity<Boolean> resultado = clienteRoteamento.existeCliente(id);
		System.out.println("Chamando ClienteService: " + resultado.getBody());
		
		return resultado;

	}
	
	
	
	
	
	
	
}
