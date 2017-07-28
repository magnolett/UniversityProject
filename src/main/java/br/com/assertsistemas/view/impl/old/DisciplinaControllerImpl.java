package br.com.assertsistemas.view.impl.old;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.service.DisciplinaService;
import br.com.assertsistemas.service.impl.DisciplinaServiceImpl;
import br.com.assertsistemas.view.DisciplinaController;

public class DisciplinaControllerImpl implements DisciplinaController {

	private static final long serialVersionUID = -141431179914689758L;
	DisciplinaService disciplinaservice;
	
	
	public DisciplinaControllerImpl(EntityManager entityManager) {
		this.disciplinaservice = new DisciplinaServiceImpl(entityManager);
	}


	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
