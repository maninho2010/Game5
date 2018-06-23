package com.game5.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game5.exception.TemaJaCadastradoException;
import com.game5.model.Tema;
import com.game5.service.TemaService;
/*
 * author Felipe Sousa
 * Classe para mapear endpoint /temas
 */
@Controller
@RequestMapping("/temas")
public class TemaController {
	
	@Autowired
	private TemaService temaService;
	
	/**
	 * 
	 * @param tema para ser feito o binding no cadastro, o próprio spring injeta ela
	 * @return view
	 */
	@GetMapping("/novo")
	public ModelAndView novo(Tema tema) {
		ModelAndView mv = new ModelAndView("tema/cadastro-tema");
		
		return mv;
	}
	
	/**
	 * Mapeia o post do cadastro de temas e salva o Tema válido
	 * 
	 * @param tema com o binding, o próprio spring injeta
	 * @param result verifica se o tema é válido, o próprio spring injeta
	 * @param attributes attributes adiciona objetos a view mesmo após o redirect, o próprio spring injeta
	 * 
	 * @return redireciona para a página de cadastro que está mapeada em /temas/novo
	 */
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Tema tema, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(tema);
		}
		
		try {
			temaService.salvar(tema);
		} catch (TemaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(tema);
		}
		
		return new ModelAndView("redirect:/temas/novo");
	}
	
}
