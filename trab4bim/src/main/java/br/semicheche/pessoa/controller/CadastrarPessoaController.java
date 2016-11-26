package br.semicheche.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.semicheche.model.PessoaModel;
import br.semicheche.repository.PessoaRepository;
import br.semicheche.usuario.controller.UsuarioController;
import br.semicheche.uteis.Uteis;

/**
 * 
 * @author lucianosemicheche
 *
 */
@Named(value="cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	//faz a injeção de uma pessoaModel
	@Inject
	PessoaModel pessoaModel;
	
	//faz a injeção de um usuarioController
	@Inject
	UsuarioController usuarioController;
	
	//faz a injeção de uma pessoaReposutory
	@Inject
	PessoaRepository pessoaRepository;
 
	/**
	 * 
	 * @return um model de uma Pessoa
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	
	/**
	 * 
	 * @param pessoaModel recebe uma Pessoa
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	/**
	 *salva um registro via Input
	 */
	public void salvarNovaPessoa(){
 
		pessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
 
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
		pessoaModel.setOrigemCadastro("I");
 
		pessoaRepository.salvarNovoRegistro(this.pessoaModel);
 
		this.pessoaModel = null;
 
		Uteis.mensagemInfo("Registro cadastrado com sucesso");
 
	}
}
