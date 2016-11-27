package br.semicheche.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.semicheche.model.PessoaModel;
import br.semicheche.repository.PessoaRepository;

/**
 * @author lucianosemicheche
 *
 * Classe para consultar as pessoas
 *
 */
@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = -2523478484767944844L;
	 
		@Inject transient
		private PessoaModel pessoaModel;
	 
		@Produces 
		private List<PessoaModel> pessoas;
	 
		@Inject transient
		private PessoaRepository pessoaRepository;
	 
		public List<PessoaModel> getPessoas() {
			return pessoas;
		}
		public void setPessoas(List<PessoaModel> pessoas) {
			this.pessoas = pessoas;
		}		
		public PessoaModel getPessoaModel() {
			return pessoaModel;
		}
		public void setPessoaModel(PessoaModel pessoaModel) {
			this.pessoaModel = pessoaModel;
		}
	 
		/**
		 * carrega a lista de pessoas ao iniciar a classe
		 */
		@PostConstruct
		public void init(){
	 
			//RETORNAR AS PESSOAS CADASTRADAS
			this.pessoas = pessoaRepository.getPessoas();
		}
		
			/***
			 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
			 * @param pessoaModel recebe uma pessoa
			 */
			public void editar(PessoaModel pessoaModel){
		 
				/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
				pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
		 
				this.pessoaModel = pessoaModel;
		 
			}
		 
			/***
			 * ATUALIZA O REGISTRO QUE FOI ALTERADO
			 */
			public void AlterarRegistro(){
		 
				this.pessoaRepository.alterarRegistro(this.pessoaModel);	
		 
		 
				/*RECARREGA OS REGISTROS*/
				this.init();
			}
	 

}
