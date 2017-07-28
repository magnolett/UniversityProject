package br.com.assertsistemas.service;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Usuario;

public interface CoordenadorService extends GenericService<Coordenador, Integer> {
	
	public Coordenador findByUsuario (Usuario usuario);


}
