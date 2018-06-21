package com.game5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game5.exception.TemaJaCadastradoException;
import com.game5.model.Tema;
import com.game5.repository.TemaRepository;

@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;
	
	public void salvar(Tema tema) throws TemaJaCadastradoException {
		
		if( temaRepository.findByNomeIgnoreCase(tema.getNome()).isPresent() ) {
			throw new TemaJaCadastradoException("Esse tema já foi cadastrado.");
		}
		
		temaRepository.save(tema);
	}
}
