package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.dao.impl.AlunoDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.AlunoService;

public class AlunoServiceImpl implements AlunoService {

	private AlunoDAO alunodao;

	private static final long serialVersionUID = -7547634483804829947L;

	public AlunoServiceImpl(EntityManager entityManager) {
		this.alunodao = new AlunoDAOImpl(entityManager);
	}

	public void insert(Aluno t) throws Exception {
		alunodao.insert(t);

	}

	public void update(Aluno t) throws Exception {
		alunodao.update(t);
	}

	public void delete(Aluno t) throws Exception {
		alunodao.delete(t);

	}

	public Aluno findById(Integer id) throws Exception {
		return alunodao.findById(id);
	}

	public List<Aluno> findAll() throws Exception {
		return alunodao.findAll();
	}

	public Aluno findByUsuario(Usuario usuario) {
		return alunodao.findByUsuario(usuario);
	}

	public Aluno findByMatricula(long matricula) {
		return alunodao.findByMatricula(matricula);
	}

	public List<Aluno> findByDisciplina(Disciplina disciplina) {
		return alunodao.findByDisciplina(disciplina);
	}
}
