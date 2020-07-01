package com.yurimiranda.administracaocompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yurimiranda.administracaocompras.entities.Estado;
import com.yurimiranda.administracaocompras.services.EstadoService;


@RestController
@RequestMapping(value = "/api/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<Estado> estados = estadoService.getAll();
		return ResponseEntity.ok().body(estados);
	}

}
