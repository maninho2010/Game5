package com.game5.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game5.exception.PalavraJaCadastradaException;
import com.game5.model.Palavra;
import com.game5.model.enums.NivelEnum;
import com.game5.repository.PalavraRepository;
import com.game5.repository.TemaRepository;
import com.game5.service.PalavraService;
/*
 * @author Felipe Sousa
 * Classe para mapear endpoint /palavras
 */
@Controller
@RequestMapping("/palavras")
public class PalavraController {
	
	@Autowired
	private PalavraRepository palavraRepository;
	
	@Autowired
	private PalavraService palavraService;
	
	@Autowired
	private TemaRepository temaRepository;
	
	/**
	 * 
	 * Mapeia a pesquisa de palavras
	 * 
	 * @return view com a lista de palavras cadastradas
	 */
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("palavra/pesquisar-palavra");
		mv.addObject("palavras", palavraRepository.findAll());
		return mv;
	}
	
	/**
	 * 
	 * Lida com requisições assíncronas para retornar uma palavra aleátoria
	 * 
	 * @return palavra cadastrada aleatória em json e status 200
	 */
	@GetMapping(value = "/random",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> randomPalavra() {
		return ResponseEntity.ok(palavraRepository.randomPalavra());
	}
	
	/**
	 * 
	 * Mapeia a página de cadastro de palavras
	 * 
	 * @param palavra para ser feito o binding no cadastro, o próprio spring injeta ela
	 * @return view com a palavra para o binding, niveis e temas existentes
	 */
	@GetMapping("/novo")
	public ModelAndView novo(Palavra palavra) {
		ModelAndView mv = new ModelAndView("palavra/cadastro-palavra");
		mv.addObject("niveis", NivelEnum.values());
		mv.addObject("temas", temaRepository.findAll());
		return mv;
	}
	
	/**
	 * 
	 * Mapeia o post do cadastro de palavras e salva a Palavra válida
	 * 
	 * @param palavra com o binding, o próprio spring injeta
	 * @param result verifica se a palavra é válida, o próprio spring injeta
	 * @param attributes adiciona objetos a view mesmo após o redirect, o próprio spring injeta
	 * 
	 * @return redireciona para a página de cadastro que está mapeada em /palavras/novo
	 */
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Palavra palavra, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(palavra);
		}
		
		try {
			palavraService.salvar(palavra);
		} catch (PalavraJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(palavra);
		}
		
		attributes.addFlashAttribute("mensagem", "palavra salva com sucesso!");
		
		return new ModelAndView("redirect:/palavras/novo");
	}
	
	/**
	 * 
	 * @param id da palavra a ser deletada
	 * @return redireciona para a pesquisa de palavras mapeada em /palavras
	 */
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		Palavra palavra = new Palavra();
		palavra.setId(id);
		palavraRepository.delete(palavra);
		return new ModelAndView("redirect:/palavras");
	}
	
	
	
}
