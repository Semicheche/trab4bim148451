package br.semicheche.model;

import java.io.Serializable;
/**
 * 
 * @author lucianosemicheche
 *Classe usada para receber os dados das paginas web.
 * 
 */
public class UsuarioModel implements Serializable {

	/**
	 * @author lucianosemicheche
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String usuario;
	private String senha;
 
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
