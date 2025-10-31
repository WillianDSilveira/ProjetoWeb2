package com.mbs.apigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.apigw.comunicacao.ClienteRoteamento;
import com.mbs.clienteServices.entidades.Cliente;




@RestController
public class ClienteController {

	@Autowired
	private ClienteRoteamento clienteRoteamento;
	
	@RequestMapping(value = "/v1/api-gw/cliente/existe-cliente/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<Boolean> existeCliente(@PathVariable Integer id) { 
		System.out.println("executando existeCliente");
		System.out.println("chamando clienteService: endpoint: existe-cliente ");
		// chamando o cliente-service
		ResponseEntity<Boolean> resultado = clienteRoteamento.existeCliente(id);
		System.out.println("Resultado clienteService: " + resultado.getBody());
		return resultado;
	}
}
	
	
	
	
