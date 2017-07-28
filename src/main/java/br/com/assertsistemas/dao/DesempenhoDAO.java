package br.com.assertsistemas.dao;

import java.util.List;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Status;

public interface DesempenhoDAO extends GenericDAO <Desempenho,Integer> {

	
	public List<Desempenho> findByAluno(Aluno aluno);
	
	public List<Desempenho> findByDisciplina (Disciplina disciplina);
	
	public List<Desempenho> findByStatusAndCoordenador (Status status, Coordenador coordenador);
	
	public Desempenho findByAlunoandDisciplina (Aluno aluno, Disciplina disciplina);
}
