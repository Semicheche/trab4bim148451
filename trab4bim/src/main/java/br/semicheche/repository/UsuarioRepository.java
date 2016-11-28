package br.semicheche.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.semicheche.model.UsuarioModel;
import br.semicheche.repository.entity.UsuarioEntity;
import br.semicheche.uteis.Uteis;

/**
 * @author lucianosemicheche
 * 
 * Classe recebe um usuarioModel e retorna um query com o usuario
 * 
 */
public class UsuarioRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
 
	/**
	 * 
	 * @param usuarioModel recebe um objeto da View
	 * 
	 * @return uma query com o resultado pego no banco
	 */
	public UsuarioEntity validaUsuario(UsuarioModel usuarioModel){
 
		try {
			
			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser) 	
			Query query = Uteis.jpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
		
			//PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
 
			//RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity)query.getSingleResult();
 
		} catch (Exception e) {
			return null;
		}
 
 
 
	}

}
