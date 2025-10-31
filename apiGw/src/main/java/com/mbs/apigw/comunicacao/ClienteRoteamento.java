package com.mbs.apigw.comunicacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbs.apigw.configuration.RetreiveMessageErrorDecoder;

import entidades.Cliente;




@FeignClient(value = "ClienteRoteamento", url = "http://localhost:9003/",
configuration = {RetreiveMessageErrorDecoder.class})
public interface ClienteRoteamento {
		
	
	@RequestMapping(value = "/v1/cliente/existeCliente",method = 
			RequestMethod.GET)
	public ResponseEntity<Boolean> existeCliente(@PathVariable Integer id);
	
	@RequestMapping(value = "/v1/cliente/buscar-cliente/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id) ;
}
