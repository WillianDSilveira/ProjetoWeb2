package com.mbs.apigw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mbs.apigw.comunicacao.VendasRoteamento;

import entidades.Venda;

public class VendasController {
	
	@Autowired
	private VendasRoteamento vendasRoteamento;
	
	
	@RequestMapping(value = "/v1/api-gw/venda",method = RequestMethod.POST)
	public ResponseEntity<String> salvarVenda(@RequestBody Venda venda){
		return vendasRoteamento.salvarVenda(venda);
	}
}
