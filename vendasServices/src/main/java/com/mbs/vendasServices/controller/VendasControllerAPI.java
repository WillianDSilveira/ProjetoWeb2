package com.mbs.vendasServices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.vendasServices.VendasServicesApplication;

import com.mbs.vendasServices.entidades.Venda;
import com.mbs.vendasServices.service.VendaService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:9002")
public class VendasControllerAPI {

	// IMPLEMENTAR
	@Autowired
	private VendaService vendasService;
	
	
	@Operation(summary = "Salva uma Venda")
	@ApiResponses(value = 
			{@ApiResponse(responseCode = "200",description = "Venda salva com Sucesso!"),
			@ApiResponse(responseCode = "400",description = "Erro na validação dos campos")})
	
	@RequestMapping(value = "/v1/vendas/salvarVenda",method = RequestMethod.POST)
	public ResponseEntity<String> salvarVenda(@RequestBody Venda venda) {
		System.out.println("executando salvar Venda " + venda);
		
		try {
			String id = vendasService.salvarVenda(venda);
			
			return ResponseEntity.ok(id);
			
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());		
		}
		
		
	}
	
	
	@Operation(summary = "Lista todas as Vendas")
    @RequestMapping(value = "/v1/vendas/listarVendas", method = RequestMethod.GET)
    public ResponseEntity<List<Venda>> listarVendas() {
        return ResponseEntity.ok(vendasService.listarVendas());
    }

    @Operation(summary = "Retorna o total das Vendas")
    @RequestMapping(value = "/v1/vendas/totalVendas", method = RequestMethod.GET)
    public ResponseEntity<Double> totalVendas() {
        return ResponseEntity.ok(vendasService.totalVendas());
    }

    @Operation(summary = "Retorna a maior Venda")
    @RequestMapping(value = "/v1/vendas/maiorVenda", method = RequestMethod.GET)
    public ResponseEntity<Venda> maiorVenda() {
        return ResponseEntity.ok(vendasService.maiorVenda());
    }

    @Operation(summary = "Retorna a menor Venda")
    @RequestMapping(value = "/v1/vendas/menorVenda", method = RequestMethod.GET)
    public ResponseEntity<Venda> menorVenda() {
        return ResponseEntity.ok(vendasService.menorVenda());
    }
	
}
