package br.com.assertsistemas.view;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Professor;

public interface CoordenadorController {

	
	public Aluno cadastroAluno();
	
	public Professor cadastroProfessor(Professor professor);
	
	public Desempenho consultaStatus();
	
	public void menu(Coordenador coordenador) ;
	
	public void draw(Object... objects);
}
