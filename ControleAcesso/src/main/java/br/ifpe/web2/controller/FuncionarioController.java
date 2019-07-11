package br.ifpe.web2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.model.Funcionario;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.service.EmpresaService;
import br.ifpe.web2.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private CargoService cargoService;
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public ModelAndView listarFuncionarios() {
		ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-list");
		mv.addObject("listaFuncionarios", this.funcionarioService.listarFuncionarios());
		return mv;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserirFuncionario() {
		ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-form");
		mv.addObject("funcionario", new Funcionario());
		mv.addObject("listaCargos", this.cargoService.listarCargos());
		mv.addObject("listaEmpresas", this.empresaService.listarEmpresas());
		mv.addObject("action", "inserir");
		return mv;
	}

	@PostMapping("/inserir")
	public ModelAndView inserirFuncionario(@Valid Funcionario funcionario, BindingResult br, RedirectAttributes ra,
			@RequestParam(name = "foto") MultipartFile foto) {
		
		if (br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-form");
			mv.addObject("action", "inserir");
			return mv;
		}
		try {
			funcionario.setFoto(foto.getBytes());
			this.funcionarioService.inserirFuncionario(funcionario);
			ra.addFlashAttribute("sucesso", "Funcionário cadastrado");
		} catch (Exception e) {
			ra.addFlashAttribute("errors", e.getMessage());
		}
		return new ModelAndView("redirect:/funcionario/inserir");
	}

}
