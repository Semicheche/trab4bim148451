package br.semicheche.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author lucianosemicheche
 * 
 * Classe que pojo de um Usuario 
 * Annotations são utilizadas para o Hibernate poder gerar as tabelas e aconsultar posteriomente
 * 
 */
@Table(name="tbl_usuario")
@Entity	
@NamedQuery(name = "UsuarioEntity.findUser", 
		    query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha")
public class UsuarioEntity implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private String codigo;
 
	@Column(name="login_usuario")
	private String usuario;
 
	@Column(name="senha_usuario")
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
