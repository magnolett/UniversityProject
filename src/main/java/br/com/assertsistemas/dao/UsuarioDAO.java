package br.com.assertsistemas.dao;

import br.com.assertsistemas.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario,Integer> {
	
	public Usuario findByLoginAndSenha(String login, String senha);

}
