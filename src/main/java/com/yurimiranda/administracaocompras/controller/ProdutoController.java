package com.yurimiranda.administracaocompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yurimiranda.administracaocompras.controller.utils.URL;
import com.yurimiranda.administracaocompras.dto.ProdutoDTO;
import com.yurimiranda.administracaocompras.entities.Produto;
import com.yurimiranda.administracaocompras.services.ProdutoService;


@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Produto produto = produtoService.getById(id);
		return ResponseEntity.ok().body(produto); 
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> getByNameAndCategorias(
			@RequestParam(value = "nomeProduto", defaultValue = "") String nomeProduto,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)
	{
		String nomeProdutoDecode = URL.decodeParam(nomeProduto);
		List<Integer> idsCategorias = URL.decodeIntList(categorias);
		Page<Produto> listaProduto = produtoService.getByNameAndCategorias(nomeProdutoDecode, idsCategorias, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaProdutoDTO = listaProduto.map(produto -> new ProdutoDTO(produto));
		return ResponseEntity.ok().body(listaProdutoDTO);
	}
}
