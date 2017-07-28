package br.com.assertsistemas.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Status;

public interface DesempenhoService extends GenericService<Desempenho, Integer> {
	
	public List<Desempenho> findByAluno(Aluno aluno);
	
	public List<Desempenho> findByDisciplina (Disciplina disciplina);
	
	public List<Desempenho> findByStatusAndCoordenador (Status status, Coordenador coordenador);
	
	public double updateMediaNotas(Desempenho desempenho, EntityManager entityManager) throws Exception;
	
	public Status resultadoDesempenho(Desempenho desempenho);
	
	public Desempenho findByAlunoandDisciplina (Aluno aluno, Disciplina disciplina);

}