package br.ifpe.web2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.model.Cargo;
import br.ifpe.web2.service.CargoService;

@Controller
@RequestMapping("/cargo")
public class CargoController {
	@Autowired
	private CargoService cargoService;
	
	@GetMapping
	public ModelAndView listarCargos() {
		ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-list");
		mv.addObject("listaCargos", this.cargoService.listarCargos());
		return mv;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserirCargo() {
		ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-form");
		mv.addObject("cargo", new Cargo());
		mv.addObject("action", "inserir");
		return mv;
	}
	
	@PostMapping("/inserir")
	public ModelAndView inserirCargo(@Valid Cargo cargo, BindingResult br) {
		if(br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-form");
			mv.addObject("action", "inserir");
			return mv;
		}
		try {
			this.cargoService.inserirCargo(cargo);
			return new ModelAndView("redirect:/cargo");
		}catch(Exception e) {
			ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-form");
			mv.addObject("error", e.getMessage());
			mv.addObject("empresa", cargo);
			mv.addObject("action", "inserir");
			return mv;
		}
	}
	
}
