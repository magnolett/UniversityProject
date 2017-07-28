package br.com.assertsistemas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Usuario.findByLoginAndSenha", query = "SELECT u FROM Usuario u WHERE u.login = :login and u.senha = :senha")

})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String login;

	private String senha;

	@Enumerated(EnumType.STRING)
	private Tipo tipoUsuario;

	public Usuario() {

	}

	public Usuario(int id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Tipo getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Tipo aluno) {
		this.tipoUsuario = aluno;
	}

}
