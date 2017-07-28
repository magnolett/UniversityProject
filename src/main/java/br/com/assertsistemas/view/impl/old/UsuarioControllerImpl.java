package br.com.assertsistemas.view.impl.old;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.UsuarioService;
import br.com.assertsistemas.service.impl.UsuarioServiceImpl;
import br.com.assertsistemas.view.UsuarioController;

public class UsuarioControllerImpl implements UsuarioController {

	private static final long serialVersionUID = -1312558941996579630L;

	EntityManager entityManager;
	UsuarioService usuarioservice;

	
	public UsuarioControllerImpl(EntityManager entityManager) {
		
		usuarioservice = new UsuarioServiceImpl(entityManager);
	}


	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	

}
