package com.yurimiranda.administracaocompras.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yurimiranda.administracaocompras.entities.Categoria;
import com.yurimiranda.administracaocompras.entities.Produto;
import com.yurimiranda.administracaocompras.repositories.CategoriaRepository;
import com.yurimiranda.administracaocompras.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto getById(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElse(null);
	}
	
	public Page<Produto> getByNameAndCategorias(String nomeProduto, List<Integer> idsCategoria,
			Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(idsCategoria);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nomeProduto, categorias, pageRequest);
	}
}
