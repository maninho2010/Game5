package com.game5.exception;
/*
 * @author Emanuel Honorio
 * Classe com exceção lançada caso tema já tenha sido cadastrado
 */
public class TemaJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TemaJaCadastradoException(String mensagem) {
		super(mensagem);
	}

}