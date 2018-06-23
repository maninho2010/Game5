package com.game5.exception;
/*
 * @author Alexandre Dias
 * Exceção que é lançada caso palavra já tenha sido cadastrada
 * 
 */
public class PalavraJaCadastradaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PalavraJaCadastradaException(String mensagem) {
		super(mensagem);
	}

}
