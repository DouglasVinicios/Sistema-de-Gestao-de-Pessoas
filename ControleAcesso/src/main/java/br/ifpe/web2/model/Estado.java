package br.ifpe.web2.model;

public enum Estado {
	PE("Pernambuco"), SP("São Paulo");
	
	String nomeEstado;
	
	Estado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
}
