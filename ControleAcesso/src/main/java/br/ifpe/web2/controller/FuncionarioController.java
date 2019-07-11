package br.ifpe.web2.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.exception.OneEmpresaExistException;
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
			@RequestParam(required = false) MultipartFile file) {

		if (br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-form");
			mv.addObject("action", "inserir");
			return mv;
		}
		try {
			if (file != null && file.getOriginalFilename().endsWith("\\.(png | jpg)")) {
				funcionario.setFoto(file.getBytes());
			}
			this.funcionarioService.inserirFuncionario(funcionario);
			ra.addFlashAttribute("sucesso", "Funcionário cadastrado");
		} catch (Exception e) {
			ra.addFlashAttribute("errors", e.getMessage());
		}
		return new ModelAndView("redirect:/funcionario/inserir");
	}

	@GetMapping("/filtrar")
	public ModelAndView filtrarFuncionario(@RequestParam(required = false) String nomeFuncionario) {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-list");
		try {
			List<Funcionario> listaFuncionarios = this.funcionarioService.filtrarFuncionarioPeloNome(nomeFuncionario);
			mv.addObject("listaEmpresas", listaFuncionarios);
		} catch (OneEmpresaExistException e) {
			String matriculaFuncionario = this.funcionarioService.findByNome(nomeFuncionario).getMatricula();
			String url = "redirect:/funcionario/atualizar/" + matriculaFuncionario;
			mv.setViewName(url);
		} catch (Exception e) {
			mv.addObject("error", "Nenhum resultado encontrado");
		}
		return mv;
	}

	@GetMapping("/atualizar/{id}")
	public ModelAndView atualizarFuncionario(@PathVariable String matricula) {
		ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-form");
		mv.addObject("funcionario", this.funcionarioService.findByMatricula(matricula));
		mv.addObject("action", "atualizar/" + matricula);
		return mv;
	}

	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarFuncionario(@Valid Funcionario funcionario, BindingResult br) {
		if (br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-form");
			mv.addObject("action", "atualizar/" + funcionario.getMatricula());
			return mv;
		}
		try {
			this.funcionarioService.inserirFuncionario(funcionario);
			return new ModelAndView("redirect:/empresa");
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("/acesso/funcionario/funcionario-form");
			mv.addObject("error", e.getMessage());
			mv.addObject("empresa", funcionario);
			mv.addObject("action", "atualizar/" + funcionario.getMatricula());
			return mv;
		}
	}

	/* fazer mudanças para melhorar esses métodos dps */
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarFuncionario(@RequestBody int[] ids) {
		for (Integer id : ids) {
			String matricula = id + "";
			Optional<Funcionario> funcionarioParaDeletar = Optional
					.ofNullable(this.funcionarioService.findByMatricula(matricula));
			if (funcionarioParaDeletar.isPresent()) {
				this.funcionarioService.deletarFuncionario(funcionarioParaDeletar.get());
			}
		}
	}
}
