package br.com.assertsistemas.dao;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Usuario;

public interface CoordenadorDAO extends GenericDAO<Coordenador,Integer> {
	
	
	public Coordenador findByUsuario (Usuario usuario);

	
	
}
