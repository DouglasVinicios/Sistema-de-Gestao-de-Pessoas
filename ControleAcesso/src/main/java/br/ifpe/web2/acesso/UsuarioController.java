package br.ifpe.web2.acesso;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/form")
	public ModelAndView exibirForm(Usuario usuario) {
		ModelAndView mv = new ModelAndView("/acesso/usuarios-form");
		mv.addObject("usuario", new Usuario());
		mv.addObject("listaPermissoes", Permissao.values());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid @ModelAttribute Usuario usuario, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + errors.getFieldErrors());
		} else {
			try {
				usuario.setAtivo(true);
				usuarioService.criarUsuario(usuario);
				ra.addFlashAttribute("mensagem", "Usuário [" + usuario.getNome() + "] criado com sucesso");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + e.getMessage());
			}
		}
		return "redirect:/";
	}

	
//	@RequestMapping(value="saveList", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView salvarPesquisarUsuarios(@Valid @ModelAttribute Usuario usuario, @RequestParam(value="action", 
//		required=false) String action, Errors errors, RedirectAttributes ra) {
//		
//		if (action != null && action.equals("salvar")) {
//			return salvar(usuario, errors, ra);
//		} else {
//			return pesquisar(usuario);
//		}
//	}

	@GetMapping("list")
	public ModelAndView pesquisar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("acesso/usuarios-list");
		if (usuario == null || usuario.getId() == null) {
			mv.addObject("lista", usuarioService.listarTodos());	
		} else {
			mv.addObject("lista", usuarioService.findUsuarioByNomeEmailAprox(usuario.getNome(), usuario.getEmail())); 	
		}
		mv.addObject("usuario", usuario);
		return mv;
	}

	@GetMapping("edit/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") Integer id) {
		Usuario usuario = usuarioService.findById(id);
		ModelAndView mv = new ModelAndView("acesso/usuarios-list");
		mv.addObject("lista", usuarioService.listarTodos());	
		mv.addObject("listaPermissoes", Permissao.values());
		mv.addObject("usuario", usuario);
		return mv;
	}

	@GetMapping("/desativar/{id}")
	public String desativar(@PathVariable("id") Integer id, RedirectAttributes ra) {
		Usuario usuario = usuarioService.findById(id);
		usuario.setAtivo(false);
		usuarioService.alterar(usuario);
		ra.addFlashAttribute("mensagemSucesso", "Usuário desativado com sucesso");
		return "redirect:/usuarios/list";
	}

	@GetMapping("/ativar/{id}")
	public String ativar(@PathVariable("id") Integer id, RedirectAttributes ra) {
		Usuario usuario = usuarioService.findById(id);
		usuario.setAtivo(true);
		usuarioService.alterar(usuario);
		ra.addFlashAttribute("mensagemSucesso", "Usuário ativado com sucesso");
		return "redirect:/usuarios/list";
	}

}
