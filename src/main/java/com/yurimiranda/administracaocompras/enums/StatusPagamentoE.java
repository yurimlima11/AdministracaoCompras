package com.yurimiranda.administracaocompras.enums;

public enum StatusPagamentoE {

	PENDENTE(1, "PAGAMENTO PENDENTE"),
	QUITADO(2, "PAGAMENTO QUITADO"),
	CANCELADO(3, "PAGAMENTO CANCELADO");

	private Integer cod;
	private String descricao;
	
	private StatusPagamentoE(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static StatusPagamentoE ToEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (StatusPagamentoE statusPagamento : StatusPagamentoE.values()) {
			if(cod.equals(statusPagamento.getCod())) {
				return statusPagamento;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod); 
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
