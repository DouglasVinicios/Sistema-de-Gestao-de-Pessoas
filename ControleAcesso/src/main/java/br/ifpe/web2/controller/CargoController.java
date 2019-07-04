package br.ifpe.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.service.CargoService;

@Controller
@RequestMapping("/cargo")
public class CargoController {
	@Autowired
	private CargoService cargoService;
	
//	@GetMapping
//	public ModelAndView listarCargos() {
//		ModelAndView mv = new ModelAndView("/acesso/cargos");
//		mv.addObject("cargos", this.cargoService.listarCargos());
//		return mv;
//	}
	
}
