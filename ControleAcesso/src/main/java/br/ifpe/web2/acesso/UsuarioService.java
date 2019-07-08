package br.ifpe.web2.acesso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.ifpe.web2.acesso.excecoes.EmailExistsException;
import br.ifpe.web2.util.CriptografiaHash;
import br.ifpe.web2.util.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private CriptografiaHash ch;
	
	public Usuario efetuarLogin(String email, String senha) throws ServiceException {
		Usuario usuario = this.usuarioDAO.efetuarLogin(email, senha);
		if (usuario == null) {
			throw new ServiceException("Login/senha não encontrados");
		}
		if (!usuario.isAtivo()) {
			throw new ServiceException("Usuário [" + usuario.getNome() + "] está bloqueado");
		}
		return usuario;
	}
	
	public Usuario findUsuarioByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}

	public Usuario alterar(Usuario novo) {
		Usuario usuarioAtual = findUsuarioByEmail(novo.getEmail());
		usuarioAtual.setNome(novo.getNome());
		return usuarioDAO.save(usuarioAtual);
	}

	public Usuario findById(Integer id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	public List<Usuario> listarTodos() {
		return usuarioDAO.findAll();
	}
	
	public void criarUsuario(Usuario usuario) throws Exception {
		
	    if (this.findUsuarioByEmail(usuario.getEmail()) != null) {
	        throw new EmailExistsException
	          ("Já existe usuário com este e-mail: " + usuario.getEmail());
	    }
	    usuario.setSenha(this.ch.passwordEncoder(usuario.getSenha()));
	 
		usuarioDAO.save(usuario);
	}
	
	public void registrarUltimoLogin(Usuario usuario) {
		this.usuarioDAO.saveAndFlush(usuario);
	}

	public Object findUsuarioByNomeEmailAprox(String nome, String email) {
		return usuarioDAO.findByNomeEmailAprox(nome, email);
	}
	
}
