package com.mbs.apigw.controller;

import java.util.EventObject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.apigw.comunicacao.ClienteRoteamento;
import com.mbs.apigw.comunicacao.VendasRoteamento;
import entidades.Cliente;
import entidades.EventoEmail;
import entidades.Venda;


@CrossOrigin(origins = "http://localhost:9005")
@RestController
public class VendasController {

	@Autowired
	private VendasRoteamento vendasRoteamento;
	
	@Autowired
	private ClienteRoteamento clienteRoteamento;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping(value = "/v1/api-gw/venda",method = RequestMethod.POST)
	public ResponseEntity<String> salvar(@RequestBody Venda venda) {
		System.out.println("executando salvar");
		return vendasRoteamento.salvarVenda(venda); // chamando vendasService
	}
	
	@RequestMapping(value = "/v1/api-gw/processar-venda",
			method = RequestMethod.POST)
	public ResponseEntity<String> processarVenda(@RequestBody Venda venda) {
		System.out.println("executando procesando venda.");
		System.out.println("Verificando se cliente existe");
		ResponseEntity<Boolean> existeCliente = 
				clienteRoteamento.existeCliente(venda.getCodCliente());
		
		if(existeCliente.getBody().equals(Boolean.FALSE)) {
			System.out.println("cliente não existe");
			return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body("Cliente não existe");
			// abortando o processamento da venda.
		}
		System.out.println("cliente existe");
		System.out.println("processando a salvar venda");		
		ResponseEntity<String> salvarVenda = vendasRoteamento.salvarVenda(venda);
		
		if(salvarVenda.getStatusCode() == HttpStatus.BAD_REQUEST) {
			System.out.println("Erro ao salvar a venda");
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao salvar a venda.");
			// abortando o processamento da venda.
		}
		System.out.println("salvar venda realizada com sucesso");
		
		String idcompra = salvarVenda.getBody();
		Cliente cliente = clienteRoteamento.buscarCliente(venda.getCodCliente()).getBody();		
		EventoEmail evento = new EventoEmail();
		evento.setVenda(venda);
		evento.setCliente(cliente);
		evento.setTituloEmail("Sucesso na compra do produto: " + venda.getNomeProduto());
		evento.setTextoEmail("Parabéns, sua compra foi aprovada, e está sendo enviada\n"
				+ "O n. do pedido da compra é: " +idcompra);
		
		System.out.println("enviando evento para o brocker");
		rabbitTemplate.convertAndSend("vendas",
				"routing-vendas",
				evento);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("sucesso");
	}
}