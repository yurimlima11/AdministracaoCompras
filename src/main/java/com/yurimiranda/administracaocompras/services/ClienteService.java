package com.yurimiranda.administracaocompras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yurimiranda.administracaocompras.dto.ClienteDTO;
import com.yurimiranda.administracaocompras.dto.ClienteNewDTO;
import com.yurimiranda.administracaocompras.entities.Categoria;
import com.yurimiranda.administracaocompras.entities.Cidade;
import com.yurimiranda.administracaocompras.entities.Cliente;
import com.yurimiranda.administracaocompras.entities.Endereco;
import com.yurimiranda.administracaocompras.enums.TipoClienteE;
import com.yurimiranda.administracaocompras.repositories.ClienteRepository;
import com.yurimiranda.administracaocompras.repositories.EnderecoRepository;
import com.yurimiranda.administracaocompras.services.exception.DataIntegrityException;
import com.yurimiranda.administracaocompras.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente getById(Integer id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado"));
	}
	
	@Transactional
	public Cliente create(Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEndereco());
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = getById(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
	
	public void delete(Integer id) {
		try {
			clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui produtos");
		}
	}
	
	public List<Cliente> findAll(){
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}
	
	public Page	<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy,
			String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(),
				clienteNewDTO.getCpfOuCnpj(), TipoClienteE.toEnum(clienteNewDTO.getTipoCliente()));
		
		Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);
		
		Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(),
				clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cidade);
		
		cliente.getEndereco().add(endereco);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		if(clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if(clienteNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cliente;
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
}
