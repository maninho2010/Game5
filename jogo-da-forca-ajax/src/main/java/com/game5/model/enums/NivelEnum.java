package com.game5.model.enums;

public enum NivelEnum {
	MUITOFACIL("Muito Fácil"),
	FACIL("Fácil"),
	MEDIO("Médio"),
	DIFICIL("Difícil"),
	MUITODIFICIL("Muito Difícil");
	
	private String descricao;
	
	NivelEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
