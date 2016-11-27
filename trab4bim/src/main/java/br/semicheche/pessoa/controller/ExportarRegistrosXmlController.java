package br.semicheche.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.semicheche.model.PessoaModel;
import br.semicheche.repository.PessoaRepository;


/**
 * 
 * @author lucianosemicheche
 *
 * Classe responsavel por exportar o arquivo .xml
 */
public class ExportarRegistrosXmlController implements Serializable {

	private static final long serialVersionUID = -7595622932050002838L;
	
	@Inject transient
	PessoaRepository pessoaRepository; 
 
	private StreamedContent arquivoDownload;
 
	/***
	 * REALIZA O DOWNLOAD DO ARQUIVO XML
	 * @return o arquivo
	 */
	public StreamedContent getArquivoDownload() {
 
		this.downlaodArquivoXmlPessoa();
 
		return arquivoDownload;
	}
 
	/***
	 * GERANDO ARQUIVO XML PARA EXPORTAÇÃO.
	 * 
	 * @return um arquivo
	 */
	private File gerarXmlPessoas(){
 
		//MASCARA PARA FORMATAR A DATA QUE VAI SER ADICIONADA NO ARQUIVO XML
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
 
		List<PessoaModel> pessoasModel = pessoaRepository.getPessoas();
 
		//ELEMENTO RAIZ DO NOSSO ARQUIVO XML
		Element elementPessoas = new Element("Pessoas");
 
		Document documentoPessoas = new Document(elementPessoas);
 
		pessoasModel.forEach(pessoa -> {
 
 
			//MONTANDO AS TAGS DO XML COM OS SEUS VALORES
			Element elementPessoa = new Element("Pessoa");			
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));
 
			//FORMATANDO A DATA
			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter);
 
			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
 
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
 
			elementPessoas.addContent(elementPessoa);
		});
 
 
		XMLOutputter xmlGerado = new XMLOutputter();
 
		try {
 
 
			/*GERANDO O NOME DO ARQUIVO*/
			String nomeArquivo =  "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
 
			//CAMINHO ONDE VAI SER GERADO O ARQUIVO XML
			File arquivo = new File("/home/lucianosemicheche/gerados".concat(nomeArquivo));
 
			FileWriter fileWriter =  new FileWriter(arquivo);
 
 
			xmlGerado.output(documentoPessoas, fileWriter);
 
			return arquivo;
 
		} catch (Exception ex) {
 
			ex.printStackTrace();
		}		
 
		return null;
	}
 
	/***
	 * PREPARA O ARQUIVO PARA DOWNLOAD
	 * 
	 * @return arquivoDonwload
	 */
	public void downlaodArquivoXmlPessoa(){
 
		File arquivoXml = this.gerarXmlPessoas();
 
		InputStream inputStream;
 
		try {
 
			inputStream = new FileInputStream(arquivoXml.getPath());
 
			arquivoDownload = new DefaultStreamedContent(inputStream,"application/xml",arquivoXml.getName());
 
		} catch (FileNotFoundException e) {
 
			e.printStackTrace();
		}

	}	

}
