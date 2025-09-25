package com.mbs.vendasServices.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.mbs.vendasServices.entidades.Venda;

import com.mbs.vendasServices.entidades.Venda;
import com.mbs.vendasServices.service.VendaService;

@Repository
public class VendaRepository {
	
	private ArrayList<Venda> lista = new ArrayList<Venda>();
	private static Integer id = 1;
	
	public String salvarVenda(Venda venda) throws Exception {
		System.out.println("executando salvar na VendaService: " + venda);
		
		venda.setNumeroVenda(id);
		id++;
		lista.add(venda);
		return venda.getNumeroVenda().toString();
	
	}
	
	
	public List<Venda> listarVendas() {
        return lista;
    }

    public Double totalVendas() {
        return lista.stream() // cria um stream a partir da lista de vendas
                    .mapToDouble(Venda::getPrecoProduto) //pega cada objeto Venda e transforma no seu preço do produto
                    .sum(); // soma todos os valores do stream e retorna o total de vendas.
    }

    public Venda maiorVenda() {
        return lista.stream() // cria um stream com todas as vendas.
                    .max((v1, v2) -> v1.getPrecoProduto().compareTo(v2.getPrecoProduto()))// encontra o objeto Venda com maior preço comparandoos
                    .orElse(null); // se a lista estiver vazia, retorna null
    }

    public Venda menorVenda() {
        return lista.stream() // cria um stream com todas as vendas.
                    .min((v1, v2) -> v1.getPrecoProduto().compareTo(v2.getPrecoProduto()))// encontra o objeto Venda com menor preço comparandoos
                    .orElse(null); // se a lista estiver vazia, retorna null
    }
}