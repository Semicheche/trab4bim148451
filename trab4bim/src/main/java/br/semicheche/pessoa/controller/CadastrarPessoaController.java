package br.semicheche.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	
	//variavel para upload do arquivo
	private UploadedFile file;
	
	/**
	 * @return retorna um arquivo
	 */
	public UploadedFile getFile() {
		return file;
	}
	
	/**
	 * @param file recebe um arquivo como parametro
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}
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
	
	public void uploadRegistros() {
		 
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		 try {


			 if(this.file.getFileName().equals("")){
				 Uteis.mensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }

			 DocumentBuilder builder = factory.newDocumentBuilder();

	                 Document doc = (Document) builder.parse(this.file.getInputstream());

	                 Element element = (Element) ((org.w3c.dom.Document) doc).getDocumentElement();

	                 NodeList nodes = ((Node) element).getChildNodes();

	                for (int i = 0; i < nodes.getLength(); i++) {

	        	     Node node  = nodes.item(i);

	        	    if(node.getNodeType() == Node.ELEMENT_NODE){

	        		 Element elementPessoa =(Element) node;

	        		 //PEGANDO OS VALORES DO ARQUIVO XML
	        		 String nome     = ((org.w3c.dom.Document) elementPessoa).getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
	        		 String sexo     = ((org.w3c.dom.Document) elementPessoa).getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String email    = ((org.w3c.dom.Document) elementPessoa).getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
	        		 String endereco = ((org.w3c.dom.Document) elementPessoa).getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

	        		 PessoaModel newPessoaModel = new PessoaModel();

	        		 newPessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
	        		 newPessoaModel.setEmail(email);
	        		 newPessoaModel.setEndereco(endereco);
	        		 newPessoaModel.setNome(nome);
	        		 newPessoaModel.setOrigemCadastro("X");
	        		 newPessoaModel.setSexo(sexo);

	        		 //SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
	        		 pessoaRepository.salvarNovoRegistro(newPessoaModel);
	        	   }
	              }



		     Uteis.mensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}


	 }
	
}
