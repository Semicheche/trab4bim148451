package br.semicheche.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.dialect.Sybase11Dialect;
/**
 * 
 * @author lucianosemicheche
 *
 *Classe Uteis contera metodos aplicaveis em todo o sistema
 *
 */
public class Uteis {
	
	/**
	 * 
	 * @return uma requisição com um entityManager
	 */
	public static EntityManager jpaEntityManager(){
		 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		ExternalContext externalContext = facesContext.getExternalContext();
 
		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
 
		return (EntityManager)request.getAttribute("entityManager");
	} 
	
		/**
		 * 
		 * @param mensagem retorna uma mensagem ao usuario
		 */
		public static void mensagem(String mensagem){
	 
			FacesContext facesContext = FacesContext.getCurrentInstance();
	 
			facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
		}
	 
		/**
		 * 
		 * @param mensagem retorna uma mensagem ao usuario
		 */
		public static void mensagemAtencao(String mensagem){
	 
			FacesContext facesContext = FacesContext.getCurrentInstance();
	 
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
		}
	 
		/**
		 * 
		 * @param mensagem retorna uma mensagem ao usuario
		 */
		public static void mensagemInfo(String mensagem){
	 
			FacesContext facesContext = FacesContext.getCurrentInstance();
	 
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
		}
	 

}
