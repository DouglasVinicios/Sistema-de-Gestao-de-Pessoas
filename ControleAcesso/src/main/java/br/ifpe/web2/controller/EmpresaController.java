package br.ifpe.web2.controller;

import java.util.List;

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
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-list");
		mv.addObject("listaEmpresas", this.empresaService.listarEmpresas());
		return mv;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserirEmpresa() {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
		mv.addObject("empresa", new Empresa());
		mv.addObject("action", "inserir");
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
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-list");
		try {
			List<Empresa> listaEmpresas = this.empresaService.filtrarEmpresaPeloNome(nomeEmpresa);
			mv.addObject("listaEmpresas", listaEmpresas);
		} catch(OneEmpresaExistException e) {
			Integer idEmpresa = this.empresaService.findByNomeOrNomeAbreviado(nomeEmpresa).getId();
			String url = "redirect:/empresa/atualizar/"+idEmpresa;
			mv.setViewName(url);
		} catch(Exception e) {
			mv.addObject("error", "Nenhum resultado encontrado");
		}
		return mv;
	}
	
	@GetMapping("/atualizar/{id}")
	public ModelAndView atualizarEmpresa(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
		mv.addObject("empresa", this.empresaService.findById(id));
		mv.addObject("action", "atualizar/"+id);
		return mv;
	}
	
	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarEmpresa(@Valid Empresa empresa, BindingResult br) {
		if(br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
			mv.addObject("action", "atualizar/"+empresa.getId());
			return mv;
		}
		try {
			this.empresaService.inserirEmpresa(empresa);
			return new ModelAndView("redirect:/empresa");
		}catch(Exception e) {
			ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-form");
			mv.addObject("error", e.getMessage());
			mv.addObject("empresa", empresa);
			mv.addObject("action", "atualizar/"+empresa.getId());
			return mv;
		}
	}
	
	
	
}
