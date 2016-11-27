package br.semicheche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.semicheche.model.PessoaModel;
import br.semicheche.model.UsuarioModel;
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
	
	/***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * 
	 * @return uma Lista de Pessoas
	 */
	public List<PessoaModel> getPessoas(){
 
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
 
		entityManager =  Uteis.jpaEntityManager();
 
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();
 
		PessoaModel pessoaModel = null;
 
		for (PessoaEntity pessoaEntity : pessoasEntity) {
 
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());
 
			if(pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");
 
 
			if(pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");
 
			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity();			
 
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
 
			pessoaModel.setUsuarioModel(usuarioModel);
 
			pessoasModel.add(pessoaModel);
		}
 
		return pessoasModel;
 
	}

		/***
		 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
		 * @param codigo
		 * @return PessoaEntity
		 */
		private PessoaEntity getPessoa(int codigo){
	 
			entityManager =  Uteis.jpaEntityManager();
	 
			return entityManager.find(PessoaEntity.class, codigo);
		}
	 
		/***
		 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
		 * @param pessoaModel
		 */
		public void alterarRegistro(PessoaModel pessoaModel){
	 
			entityManager =  Uteis.jpaEntityManager();
	 
			PessoaEntity pessoaEntity = this.getPessoa(pessoaModel.getCodigo());
	 
			pessoaEntity.setEmail(pessoaModel.getEmail());
			pessoaEntity.setEndereco(pessoaModel.getEndereco());
			pessoaEntity.setNome(pessoaModel.getNome());
			pessoaEntity.setSexo(pessoaModel.getSexo());
	 
			entityManager.merge(pessoaEntity);
		}

}
