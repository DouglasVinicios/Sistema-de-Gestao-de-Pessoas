package br.ifpe.web2.acesso;

public enum Permissao {

	ADMIN("Administrador"), PADRAO("Usuário padrão");
	
	public String texto;
	
	Permissao(String t) {
		texto = t;
	}
}
