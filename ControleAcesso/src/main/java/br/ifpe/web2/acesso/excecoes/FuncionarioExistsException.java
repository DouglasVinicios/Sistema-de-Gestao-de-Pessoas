package br.ifpe.web2.acesso.excecoes;

public class FuncionarioExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionarioExistsException(String message) {
		super(message);
	}
}
