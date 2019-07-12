package br.ifpe.web2.acesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.email = :email")
	public Usuario findByEmail(String email);

	@Query("select u from Usuario u where u.nome like %:nome% or u.email like %:email%")
	public Usuario findByNomeEmailAprox(String nome, String email); 
	
	@Query("select u from Usuario u where u.email = :email and u.senha = :senha")
	public Usuario efetuarLogin(String email, String senha);
}
