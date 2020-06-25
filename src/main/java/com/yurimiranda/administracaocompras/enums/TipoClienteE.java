package com.yurimiranda.administracaocompras.enums;

public enum TipoClienteE {

	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;

	private TipoClienteE(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static TipoClienteE toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (TipoClienteE x : TipoClienteE.values()) {
			if(cod.equals(x.getCod())) {
				return x;
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
