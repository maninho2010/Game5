package com.game5.model.enums;

/*
 * @author Pedro Vieira
 * Enum criado para 
 * classficar nível de dificuldade da palavra a ser advinhada
 * 
 */
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
