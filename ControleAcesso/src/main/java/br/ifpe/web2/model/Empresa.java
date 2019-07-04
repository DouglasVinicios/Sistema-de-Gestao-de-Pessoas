package br.ifpe.web2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Informe nome")
	@Size(max = 50)
	@Column(length = 50, nullable = false)
	private String nome;
	@NotBlank(message = "Informe nome abreviado")
	@Size(max = 10)
	@Column(length = 10)
	private String nomeAbreviado;
	@NotBlank(message = "Informe o email")
	@Size(max = 80)
	@Column(length = 80)
	private String email;
	@NotNull(message = "Informe empresa principal")
	@Column(columnDefinition = "bit(1) default 0")
	private boolean indicadorEmpresaPrincipal;
	@Column(columnDefinition = "bit(1) default 1")
	private boolean situacao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}
	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}
	public void setIndicadorEmpresaPrincipal(boolean indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}
	public boolean isSituacao() {
		return situacao;
	}
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", nomeAbreviado=" + nomeAbreviado + ", email=" + email
				+ ", indicadorEmpresaPrincipal=" + indicadorEmpresaPrincipal + ", situacao=" + situacao + "]";
	}
}
