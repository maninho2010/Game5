package com.game5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game5.exception.PalavraJaCadastradaException;
import com.game5.model.Palavra;
import com.game5.repository.PalavraRepository;

/**
 * @author Emanuel Honorio
 * 
 * Classe que aplica as regras de persistencia para Palavra
 *
 */
@Service
public class PalavraService {

	@Autowired
	PalavraRepository palavraRepository;
	
	/**
	 * Salva aplicando as regras de negócio
	 * 
	 * @param palavra a ser salva
	 * @throws PalavraJaCadastradaException se a palavra ja tenha sido cadastrada
	 */
	public void salvar(Palavra palavra) throws PalavraJaCadastradaException {
		
		if( palavraRepository.findByNomeIgnoreCase(palavra.getNome()).isPresent() ) {
			throw new PalavraJaCadastradaException("Palavra já foi cadastrada.");
		}
		
		palavraRepository.save(palavra);
	}
}
