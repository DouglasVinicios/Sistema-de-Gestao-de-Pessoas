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
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.exception.OneEmpresaExistException;
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
	
	@GetMapping("/filtrar")
	public ModelAndView filtrarCargo(@RequestParam(required = false) String nomeCargo) {
		ModelAndView mv = new ModelAndView("/acesso/empresa/empresa-list");
		try {
			List<Cargo> listaCargos = this.cargoService.filtrarCargoPeloNome(nomeCargo);
			mv.addObject("listaCargos", listaCargos);
		} catch (OneEmpresaExistException e) {
			Integer idCargo = this.cargoService.findByNome(nomeCargo).get(0).getId();
			String url = "redirect:/cargo/atualizar/" + idCargo;
			mv.setViewName(url);
		} catch (Exception e) {
			mv.addObject("error", "Nenhum resultado encontrado");
		}
		return mv;
	}

	@GetMapping("/atualizar/{id}")
	public ModelAndView atualizarCargo(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-form");
		mv.addObject("empresa", this.cargoService.findById(id));
		mv.addObject("action", "atualizar/" + id);
		return mv;
	}

	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarCargo(@Valid Cargo cargo, BindingResult br) {
		if (br.hasErrors()) {
			ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-form");
			mv.addObject("action", "atualizar/" + cargo.getId());
			return mv;
		}
		try {
			this.cargoService.inserirCargo(cargo);
			return new ModelAndView("redirect:/cargo");
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("/acesso/cargo/cargo-form");
			mv.addObject("error", e.getMessage());
			mv.addObject("empresa", cargo);
			mv.addObject("action", "atualizar/" + cargo.getId());
			return mv;
		}
	}
	
	/* fazer mudanças para melhorar esses métodos dps */
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCargo(@RequestBody int[] ids) {
		for (Integer id : ids) {
			Optional<Cargo> cargoParaDeletar = this.cargoService.findById(id);
			if (cargoParaDeletar.isPresent()) {
				this.cargoService.deletarCargo(cargoParaDeletar.get());
			}
		}
	}
	
}
