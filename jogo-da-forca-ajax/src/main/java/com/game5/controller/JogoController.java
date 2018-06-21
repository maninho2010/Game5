package com.game5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.game5.repository.PalavraRepository;

@Controller
@RequestMapping("/jogar")
public class JogoController {
	
	@Autowired
	PalavraRepository palavraRepository;
	
	@GetMapping
	public ModelAndView jogar() {
		
		ModelAndView mv = new ModelAndView("jogo");
		
		mv.addObject("numDePalavras", palavraRepository.count());
		return mv;
	}
	
}
