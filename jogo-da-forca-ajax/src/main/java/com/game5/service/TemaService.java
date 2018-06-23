package com.game5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game5.exception.TemaJaCadastradoException;
import com.game5.model.Tema;
import com.game5.repository.TemaRepository;

/**
 * @author Emanuel Honorio
 * 
 * Classe que aplica as regras de persistencia para Tema
 *
 */
@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;
	
	
	/**
	 * Salva aplicando as regras de negócio
	 * 
	 * @param tema a ser salvo
	 * @throws TemaJaCadastradoException se o tema ja tenha sido cadastrado
	 */
	public void salvar(Tema tema) throws TemaJaCadastradoException {
		
		if( temaRepository.findByNomeIgnoreCase(tema.getNome()).isPresent() ) {
			throw new TemaJaCadastradoException("Esse tema já foi cadastrado.");
		}
		
		temaRepository.save(tema);
	}
}
