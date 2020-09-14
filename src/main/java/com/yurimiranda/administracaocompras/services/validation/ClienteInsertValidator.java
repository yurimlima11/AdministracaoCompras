package com.yurimiranda.administracaocompras.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yurimiranda.administracaocompras.controller.exception.FieldMessage;
import com.yurimiranda.administracaocompras.dto.ClienteNewDTO;
import com.yurimiranda.administracaocompras.enums.TipoClienteE;
import com.yurimiranda.administracaocompras.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{


	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> lista = new ArrayList<>();
		
		if(clienteNewDTO.getTipoCliente().equals(TipoClienteE.PESSOA_FISICA.getCod()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			lista.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if(clienteNewDTO.getTipoCliente().equals(TipoClienteE.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			lista.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		for (FieldMessage fieldMessage : lista) {
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		return lista.isEmpty();
	}
	
}
