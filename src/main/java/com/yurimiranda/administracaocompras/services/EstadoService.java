package com.yurimiranda.administracaocompras.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurimiranda.administracaocompras.entities.Estado;
import com.yurimiranda.administracaocompras.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> getAll(){
		List<Estado> estados = estadoRepository.findAll();
		return estados;
	}
}
