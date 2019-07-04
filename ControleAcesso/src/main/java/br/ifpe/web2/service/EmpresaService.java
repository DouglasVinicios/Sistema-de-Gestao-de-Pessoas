package br.ifpe.web2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.EmpresaDAO;
import br.ifpe.web2.exception.OneEmpresaExistException;
import br.ifpe.web2.model.Empresa;

@Service
public class EmpresaService {
	@Autowired
	private EmpresaDAO empresaRep;
	
	public List<Empresa> listarEmpresas() {
		return this.empresaRep.findAll(Sort.by("nome"));
	}
	
	public Optional<Empresa> findById(Integer id) {
		return this.empresaRep.findById(id);
	}
	
	public Optional<Empresa> findByNome(String nomeEmpresa) {
		return this.empresaRep.findByNome(nomeEmpresa);
	}
	
	public void inserirEmpresa(Empresa empresa) throws Exception {
		if(this.empresaRep.findByNome(empresa.getNome()).isPresent()) {
			throw new Exception("Já existe empresa com o nome informado");
		}
		for (Empresa e : this.empresaRep.findByIndicadorEmpresaPrincipal(empresa.isIndicadorEmpresaPrincipal())) {
			if(e.isIndicadorEmpresaPrincipal()) {
				throw new Exception("Já existe empresa principal");
			}
		}
		this.empresaRep.save(empresa);
	}
	
	public List<Empresa> filtrarEmpresaPeloNome(String nomeEmpresa) throws OneEmpresaExistException, Exception {
		if(!nomeEmpresa.trim().isEmpty()) {
			List<Empresa> empresas = Optional.ofNullable(this.empresaRep.findByNomeOrNomeAbreviado(nomeEmpresa)).orElseThrow(Exception::new);
			if(empresas.size() == 1) {
				throw new OneEmpresaExistException();
			}
			return empresas;
		}
		return this.listarEmpresas();
	}
}
