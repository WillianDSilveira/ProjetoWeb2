package com.mbs.vendasServices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.vendasServices.entidades.Venda;
import com.mbs.vendasServices.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	public String salvarVenda(Venda venda) throws Exception {
		System.out.println("Executando VendasSalvar " + venda);
		
		if (venda.getNumeroVenda() == null || venda.getNumeroVenda() <= 0) {
		    throw new Exception("Número da venda deve ser informado e maior que 0");
		}

		if (venda.getCodCliente() == null || venda.getCodCliente() <= 0) {
		    throw new Exception("Código do cliente deve ser informado e maior que 0");
		}

		if (venda.getNomeProduto() == null || venda.getNomeProduto().length() < 3) {
		    throw new Exception("Nome do produto deve ter no mínimo 3 caracteres");
		}

		if (venda.getPrecoProduto() == null || venda.getPrecoProduto() <= 0) {
		    throw new Exception("Preço do produto deve ser informado e maior que 0");
		}
		
		return vendaRepository.salvarVenda(venda);
		
	}
	
	
	public List<Venda> listarVendas() {
        return vendaRepository.listarVendas();
    }

    public Double totalVendas() {
        return vendaRepository.totalVendas();
    }

    public Venda maiorVenda() {
        return vendaRepository.maiorVenda();
    }

    public Venda menorVenda() {
        return vendaRepository.menorVenda();
    }
	
	
	
	
}