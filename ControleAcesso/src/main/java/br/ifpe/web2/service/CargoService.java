package br.ifpe.web2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.CargoDAO;
import br.ifpe.web2.exception.OneCargoExistException;
import br.ifpe.web2.exception.OneEmpresaExistException;
import br.ifpe.web2.model.Cargo;

@Service
public class CargoService {
	@Autowired
	private CargoDAO cargoRep;
	
	public List<Cargo> listarCargos() {
		return this.cargoRep.findAll(Sort.by("nome"));
	}
	
	public Optional<Cargo> findById(Integer id) {
		return this.cargoRep.findById(id);
	}

	public List<Cargo> findByNome(String nomeCargo) {
		return this.cargoRep.findByNome(nomeCargo);
	}

	public void inserirCargo(Cargo cargo) throws Exception {
		Optional<Cargo> cargoOptional = Optional.ofNullable(this.cargoRep.findByDescricao(cargo.getDescricao()));
		if (cargoOptional.isPresent()) {
			throw new Exception("Já existe cargo com essa descrição informada");
		}
		this.cargoRep.save(cargo);
	}

	public List<Cargo> filtrarCargoPeloNome(String nomeCargo) throws OneCargoExistException, Exception {
		if (!nomeCargo.trim().isEmpty()) {
			Optional<List<Cargo>> cargos = Optional
					.ofNullable(this.cargoRep.findByNome(nomeCargo));
			if (cargos.isPresent()) {

				if (cargos.get().size() == 1) {
					throw new OneEmpresaExistException();
				}
				if (cargos.get().size() == 0) {
					throw new Exception();
				}
				return cargos.get();
			}
		}
		return listarCargos();
	}
	
	public void deletarCargo(Cargo cargo) {
		this.cargoRep.delete(cargo);
	}
}
