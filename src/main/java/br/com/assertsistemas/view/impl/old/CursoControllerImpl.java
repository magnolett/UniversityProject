package br.com.assertsistemas.view.impl.old;

import javax.persistence.EntityManager;

import br.com.assertsistemas.service.CursoService;
import br.com.assertsistemas.service.impl.CursoServiceImpl;
import br.com.assertsistemas.view.CursoController;

public class CursoControllerImpl implements CursoController {

	private static final long serialVersionUID = 659508578789728978L;
	private CursoService cursoservice;

	public CursoControllerImpl(EntityManager entityManager) {
		this.cursoservice = new CursoServiceImpl(entityManager);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
