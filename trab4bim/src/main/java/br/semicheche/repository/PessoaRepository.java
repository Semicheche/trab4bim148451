package br.semicheche.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.semicheche.model.PessoaModel;
import br.semicheche.repository.entity.PessoaEntity;
import br.semicheche.repository.entity.UsuarioEntity;
import br.semicheche.uteis.Uteis;
/**
 * 
 * @author lucianosemicheche
 * 
 * Classe responsavel para persistir no banco
 *
 */
public class PessoaRepository {
	
	@Inject
	PessoaEntity pessoaEntity;
 
	EntityManager entityManager;
 
	/***
	 * metodo responsavel para salvar no banco
	 * 
	 * @param pessoaModel recebe um modelo de uma pessoa para persistir no banco
	 * 
	 */
	public void salvarNovoRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.jpaEntityManager();
 
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());
 
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); 
 
		pessoaEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(pessoaEntity);
 
	}

}
