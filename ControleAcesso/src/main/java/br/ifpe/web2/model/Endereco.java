package br.ifpe.web2.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {
	@NotBlank(message = "Informe o logradouro")
	@Column(length = 20)
	private String logradouro;
	@NotBlank(message = "Informe o complemento")
	@Column(length = 10)
	private String complemento;
	@NotNull(message = "Informe o número")
	@Size(max = 4)
	@Column(length = 4)
	private int numero;
	@NotBlank(message = "Informe a cidade")
	@Column(length = 40)
	private String cidade;
	@NotBlank(message = "Informe o estado")
	@Size(max = 2)
	@Column(length = 2, columnDefinition = "char(2)")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	@NotNull(message = "Informe o cep")
	@Size(max = 5)
	@Column(length = 5)
	private String cep;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
