package com.mbs.apigw.comunicacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entidades.Venda;

@FeignClient(value = "VendasRoteamento", url = "http://localhost:9002/")
public interface VendasRoteamento {

	@RequestMapping(value = "/v1/vendas/salvarVenda",method = RequestMethod.POST)
	public ResponseEntity<String> salvarVenda(@RequestBody Venda venda);
}
