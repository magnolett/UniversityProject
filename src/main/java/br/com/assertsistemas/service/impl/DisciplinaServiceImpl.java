package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.DisciplinaDAO;
import br.com.assertsistemas.dao.impl.DisciplinaDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.service.DisciplinaService;

public class DisciplinaServiceImpl implements DisciplinaService {

	private DisciplinaDAO disciplinadao;

	private static final long serialVersionUID = 1421935467846694085L;

	public DisciplinaServiceImpl(EntityManager entityManager) {
		this.disciplinadao = new DisciplinaDAOImpl(entityManager);
	}


	public void insert(Disciplina t) throws Exception {
		disciplinadao.insert(t);

	}

	public void update(Disciplina t) throws Exception {
		disciplinadao.update(t);

	}

	public void delete(Disciplina t) throws Exception {
		disciplinadao.delete(t);

	}

	public Disciplina findById(Integer id) throws Exception {
		return disciplinadao.findById(id);

	}

	public List<Disciplina> findAll() throws Exception {
		return disciplinadao.findAll();

	}

	public Disciplina findByCodigo(long codigo) {
		return disciplinadao.findByCodigo(codigo);

	}

	public List<Disciplina> findByCurso(Curso curso) {
		return disciplinadao.findByCurso(curso);

	}

	public List<Disciplina> findByAluno(Aluno aluno) {
		return disciplinadao.findByAluno(aluno);

	}

	@Override
	public List<Disciplina> findByProfessor(Professor professor) {
		return disciplinadao.findByProfessor(professor);
	}

}
