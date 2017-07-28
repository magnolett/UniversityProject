package br.com.assertsistemas.dao;

import java.util.List;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;

public interface CursoDAO extends GenericDAO<Curso,Integer> {

	
	public Curso findByNome (String nome);
	
	public List<Curso> findByCoordenador (Coordenador coordenador);
	
	public List<Curso> findByDisciplina (Disciplina disciplina);
	
}
