package br.com.assertsistemas.dao;

import java.util.List;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;

public interface DisciplinaDAO extends GenericDAO<Disciplina,Integer> {
	
	public Disciplina findByCodigo (long codigo);
	
	public List<Disciplina> findByCurso (Curso curso);
	
	public List<Disciplina> findByAluno (Aluno aluno);
	
	public List<Disciplina> findByProfessor(Professor professor);

	
	
}
