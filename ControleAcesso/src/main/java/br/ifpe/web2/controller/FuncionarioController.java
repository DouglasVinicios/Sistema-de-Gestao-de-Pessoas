package br.ifpe.web2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.model.Funcionario;
import br.ifpe.web2.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ModelAndView listarFuncionarios() {
		ModelAndView mv = new ModelAndView("/acesso/funcionario");
		mv.addObject("listaFuncionarios", this.funcionarioService.listarFuncionarios());
		return mv;
	}
	
	@PostMapping
	public ModelAndView inserirFuncionario(@Valid Funcionario funcionario, BindingResult br, RedirectAttributes ra) {
		
		if(br.hasErrors()) {
			ModelAndView mv = new ModelAndView("redirect:/");
			ra.addFlashAttribute("errors", br.getAllErrors());
			return mv;
		}
		ModelAndView mv = new ModelAndView("/acesso/funcionario");
		return mv;
	}
	
}
