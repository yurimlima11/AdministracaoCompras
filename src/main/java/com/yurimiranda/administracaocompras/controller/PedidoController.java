package com.yurimiranda.administracaocompras.controller;

import java.net.URI;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yurimiranda.administracaocompras.entities.Pedido;
import com.yurimiranda.administracaocompras.services.PedidoService;


@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Integer id){
		Pedido pedido = pedidoService.getById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Pedido obj){
		obj = pedidoService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
