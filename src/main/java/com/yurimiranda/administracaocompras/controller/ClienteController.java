package com.yurimiranda.administracaocompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yurimiranda.administracaocompras.entities.Cliente;
import com.yurimiranda.administracaocompras.services.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Integer id){
		Cliente cliente = clienteService.getById(id);
		return ResponseEntity.ok().body(cliente);
	}
}
