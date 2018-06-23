package com.game5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/*
 * @author Felipe Sousa
 * Controller criada para mapear endpoint /
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	
	@GetMapping
	public ModelAndView home() {
		return new ModelAndView("redirect:/jogar");
	}
}
