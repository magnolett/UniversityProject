package br.com.assertsistemas.view;

import br.com.assertsistemas.entity.Professor;

public interface ProfessorController extends GenericController {

	public void cadastrarNotas(Professor professor);
	
	public void alterarNotas(Professor professor);
	
	public void consultarAlunos(Professor professor);
	
	public void menu(Professor professor);


	
}


