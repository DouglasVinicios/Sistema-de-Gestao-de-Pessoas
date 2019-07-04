package br.ifpe.web2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.exception.OneEmpresaExistException;
import br.ifpe.web2.model.Empresa;
import br.ifpe.web2.service.EmpresaService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public ModelAndView listarEmpresas() {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa");
		mv.addObject("listaEmpresas", this.empresaService.listarEmpresas());
		return mv;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserirEmpresa() {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
		mv.addObject("empresa", new Empresa());
		return mv;
	}
	
	@PostMapping("/inserir")
	public ModelAndView inserirEmpresa(@Valid Empresa empresa, BindingResult br) {
		if(br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
			return mv;
		}
		try {
			this.empresaService.inserirEmpresa(empresa);
			return new ModelAndView("redirect:/empresa");
		}catch(Exception e) {
			ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
			mv.addObject("error", e.getMessage());
			mv.addObject("empresa", empresa);
			return mv;
		}
	}
	
	@GetMapping("/filtrar")
	public ModelAndView filtrarEmpresa(@RequestParam(required = false) String nomeEmpresa) {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa");
		try {
			mv.addObject("listaEmpresas", this.empresaService.filtrarEmpresaPeloNome(nomeEmpresa));
		} catch(OneEmpresaExistException e) {
			System.out.println(this.empresaService.findByNome(nomeEmpresa));
			//this.atualizarEmpresa(this.empresaService.findByNome(nomeEmpresa).get().getId());
		} catch(Exception e) {
			mv.setViewName("/acesso/empresa/empresa-form");
			mv.addObject("error", "Nenhum resultado encontrado");
		}
		return mv;
	}
	
	@GetMapping("/atualizar/{id}")
	public ModelAndView atualizarEmpresa(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
		mv.addObject("empresa", this.empresaService.findById(id));
		return mv;
	}
	
	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarEmpresa(@Valid Empresa empresa, BindingResult br) {
		if(br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
			return mv;
		}
		try {
			this.empresaService.inserirEmpresa(empresa);
			return new ModelAndView("redirect:/empresa");
		}catch(Exception e) {
			ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
			mv.addObject("error", e.getMessage());
			mv.addObject("empresa", empresa);
			return mv;
		}
	}
	
	
	
}
