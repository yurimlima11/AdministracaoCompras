package com.yurimiranda.administracaocompras.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurimiranda.administracaocompras.entities.Pedido;
import com.yurimiranda.administracaocompras.repositories.PedidoRepository;
import com.yurimiranda.administracaocompras.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido getById(Integer id){
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(()  -> new ObjectNotFoundException(
				"Pedido não encontrado"));
	}
}
