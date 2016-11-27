package br.semicheche.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.semicheche.repository.PessoaRepository;

/**
 * @author lucianosemicheche
 *
 *Classe responsavel pela criação do grafico de Pizza
 *
 */
@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {
	 
		@Inject
		private PessoaRepository pessoaRepository;
	 
	 
		private PieChartModel pieChartModel;
		
		//method retonrna um pieChartModel
		public PieChartModel getPieChartModel() {
			return pieChartModel;
		}
		
		//metodo inicializa apos a instancia da classe
		@PostConstruct
		public  void init(){
	 
			this.pieChartModel = new PieChartModel();
	 
			this.montaGrafico();
		}
	 
		/***
		 * MONTA O GRÁFICO NA PÁGINA
		 */
		private void montaGrafico(){
	 
			//CONSULTA OS DADOS PARA MONTAR O GRÁFICO
			Hashtable<String, Integer> hashtableRegistros = pessoaRepository.getOrigemPessoa();
	 
	 
			//INFORMANDO OS VALORES PARA MONTAR O GRÁFICO
			hashtableRegistros.forEach((chave,valor) -> {
	 
	 
				pieChartModel.set(chave, valor);
	 
			});
	 
			pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
			pieChartModel.setShowDataLabels(true);
			pieChartModel.setLegendPosition("e");
	 
	 
		}

}
