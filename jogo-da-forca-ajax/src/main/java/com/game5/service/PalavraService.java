package com.game5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game5.exception.PalavraJaCadastradaException;
import com.game5.model.Palavra;
import com.game5.repository.PalavraRepository;

@Service
public class PalavraService {

	@Autowired
	PalavraRepository palavraRepository;
	
	public void salvar(Palavra palavra) throws PalavraJaCadastradaException {
		
		if( palavraRepository.findByNomeIgnoreCase(palavra.getNome()).isPresent() ) {
			throw new PalavraJaCadastradaException("Palavra já foi cadastrada.");
		}
		
		palavraRepository.save(palavra);
	}
}
