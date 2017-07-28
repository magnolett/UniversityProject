package br.com.assertsistemas.service;

import br.com.assertsistemas.entity.Pessoa;
import br.com.assertsistemas.entity.Usuario;

public interface UsuarioService extends GenericService<Usuario, Integer> {
	
	public Pessoa autentica(String login, String senha) ;


}
