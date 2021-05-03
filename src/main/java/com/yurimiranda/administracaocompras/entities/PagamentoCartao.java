package com.yurimiranda.administracaocompras.entities;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.yurimiranda.administracaocompras.enums.StatusPagamentoE;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoCartao extends Pagamento{

	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Integer id, StatusPagamentoE estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
}
