package br.com.assertsistemas.dao;

import java.util.List;

import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Usuario;

public interface ProfessorDAO extends GenericDAO<Professor,Integer> {
	
	public List<Professor> findByQualificacao (String qualificacao);
	
	public Professor findByDisciplina (Disciplina disciplina);
	
	public Professor findByUsuario (Usuario usuario);
}
