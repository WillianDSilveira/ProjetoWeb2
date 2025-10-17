package com.mbs.apigw.comunicacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

;


@FeignClient(value = "ClienteRoteamento", url = "http://localhost:9003/")
public interface ClienteRoteamento {
		
	
	@RequestMapping(value = "/v1/cliente/existeCliente",method = 
			RequestMethod.GET)
	public ResponseEntity<Boolean> existeCliente(@PathVariable Integer id);
	
	
}
