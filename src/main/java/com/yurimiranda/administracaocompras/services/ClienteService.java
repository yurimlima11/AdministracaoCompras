package com.yurimiranda.administracaocompras.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurimiranda.administracaocompras.entities.Cliente;
import com.yurimiranda.administracaocompras.repositories.ClienteRepository;
import com.yurimiranda.administracaocompras.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente getById(Integer id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado"));
	}

}
