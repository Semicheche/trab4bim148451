package br.semicheche.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.semicheche.model.PessoaModel;
import br.semicheche.repository.PessoaRepository;

/**
 * @author lucianosemicheche
 * 
 * Classe responsavel por trazer as pessoas para a consulta com carrousel
 * 
 */
@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	@Inject transient
	private PessoaRepository pessoaRepository;
 
	@Produces 
	private List<PessoaModel> pessoas;
	
	//return uma lista de Pessoas
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	
	/**
	 * metodo que inicia ao instanciar a classe
	 */
	@PostConstruct
	private void init(){
 
		this.pessoas = pessoaRepository.getPessoas();
	}

}
