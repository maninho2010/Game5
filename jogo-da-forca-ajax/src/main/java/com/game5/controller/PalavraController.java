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
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("palavra/pesquisar-palavra");
		mv.addObject("palavras", palavraRepository.findAll());
		return mv;
	}
	
	@GetMapping(value = "/random",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> randomPalavra() {
		return ResponseEntity.ok(palavraRepository.randomPalavra());
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Palavra palavra) {
		ModelAndView mv = new ModelAndView("palavra/cadastro-palavra");
		mv.addObject("niveis", NivelEnum.values());
		mv.addObject("temas", temaRepository.findAll());
		return mv;
	}
	
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
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		Palavra palavra = new Palavra();
		palavra.setId(id);
		palavraRepository.delete(palavra);
		return new ModelAndView("redirect:/palavras");
	}
	
	
	
}
