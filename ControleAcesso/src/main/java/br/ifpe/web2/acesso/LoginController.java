package br.ifpe.web2.acesso;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.util.ServiceException;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping({"/", "/login"})
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/efetuarLogin")
	public String efetuarLogin(Usuario usuario, RedirectAttributes ra, HttpSession session) {
		Usuario usuarioLogado;
		try {
			usuarioLogado = this.usuarioService.efetuarLogin(usuario.getEmail(), usuario.getSenha());
			session.setAttribute("usuarioLogado", usuarioLogado);
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagemErro", e.getMessage());
			return "redirect:/";
		} 
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/acesso-negado")
	public String acessoNegado() {
		return "acesso-negado";
	}
	
	@GetMapping("/novo-usuario")
	public String novoUsuario() {
		return "redirect:/usuarios/form";
	}
	
}
