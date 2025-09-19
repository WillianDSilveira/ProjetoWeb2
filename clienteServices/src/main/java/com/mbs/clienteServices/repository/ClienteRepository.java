package com.mbs.clienteServices.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.mbs.clienteServices.entidades.Cliente;

@Repository
public class ClienteRepository {
	
	
	private ArrayList<Cliente> lista = new ArrayList<Cliente>();
	private static Integer id = 1;
	
	public String salvar(Cliente cliente) throws Exception {
		System.out.println("executando salvar na Service: " + cliente);
		
		cliente.setId(id);
		id++;
		lista.add(cliente);
		return cliente.getId().toString();
	}
	
	
	public List<Cliente> listar() {
		return lista;
	}
	
	
	public boolean deletar(Integer id) {
		return lista
				.removeIf(obj -> obj.getId().equals(id));
	}
		
	
	
	public boolean atualizar(Cliente cliente) {
	    for (Cliente c : lista) {
	        if (c.getId().equals(cliente.getId())) {	            
	            c.setNome(cliente.getNome());
	            c.setEmail(cliente.getEmail());
	            c.setCpf(cliente.getCpf());
	            c.setCep(cliente.getCep());
	            return true;
	            
	        }
	    }
	    return false;
	   
	}

	
	
	
	
	public Boolean existeCliente(Cliente cliente) {
		for (Cliente c : lista) {
			if(c.getId().equals(id)) {
				return true;
			}
			
		}
		 return false;
		
		
	}
		
	
	
	
	public Cliente buscarCliente(Integer id) {
	    for (Cliente c : lista) {
	        if (c.getId().equals(id)) {
	            
	        	return c;
	        }
	    }
	    return null;	    
	}

}
