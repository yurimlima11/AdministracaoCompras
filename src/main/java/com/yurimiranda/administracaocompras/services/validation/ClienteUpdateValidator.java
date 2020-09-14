package com.yurimiranda.administracaocompras.services.validation;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.yurimiranda.administracaocompras.controller.exception.FieldMessage;
import com.yurimiranda.administracaocompras.dto.ClienteDTO;
import com.yurimiranda.administracaocompras.entities.Cliente;
import com.yurimiranda.administracaocompras.repositories.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> lista = new ArrayList<>();
		
		Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());
		
		if(cliente != null && !cliente.getId().equals(uriId)) {
			lista.add(new FieldMessage("email", "E-mail já está em uso"));
		}
		
		for (FieldMessage fieldMessage : lista) {
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		return lista.isEmpty();
	}
	
}
