package br.semicheche.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.semicheche.model.UsuarioModel;
import br.semicheche.repository.UsuarioRepository;
import br.semicheche.repository.entity.UsuarioEntity;
import br.semicheche.uteis.Uteis;
/**
 * 
 * @author lucianosemicheche
 * 
 *Classe possui as injeções de dependencia e faz a intermediação entre a view e o model
 *
 */
@Named(value="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioModel usuarioModel;
 
	@Inject
	private UsuarioRepository usuarioRepository;
 
	@Inject
	private UsuarioEntity usuarioEntity;
	
	/**
	 * o método
	 * @return um usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	
	/**
	 * o metodo recebe como @param usuarioModel
	 * 
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	/**
	 * o Metodo
	 * @return um usuario autenticado
	 */
	public UsuarioModel getUsuarioSession(){
 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
	
	/**
	 * o metodo
	 * @return o parametro para encerrar a sessão
	 */
	public String logout(){
 
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 
		return "/index.xhtml?faces-redirect=true";
	}
	/**
	 * o metodo verifica se nenehum campo esta nulo e
	 * @return
	 */
	public String efetuarLogin(){
 
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
 
			Uteis.mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
 
			Uteis.mensagem("Favor informara senha!");
			return null;
		}
		else{	
 
			usuarioEntity = usuarioRepository.validaUsuario(usuarioModel);
 
			if(usuarioEntity!= null){
 
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());
 
 
				FacesContext facesContext = FacesContext.getCurrentInstance();
 
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
 
 
				return "sistema/home?faces-redirect=true";
			}
			else{
 
				Uteis.mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
 
 
	}
 

}
