package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.CursoDAO;
import br.com.assertsistemas.dao.impl.CursoDAOImpl;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.service.CursoService;

public class CursoServiceImpl implements CursoService {

	private CursoDAO cursodao;

	private static final long serialVersionUID = -8941785242848016265L;

	public CursoServiceImpl(EntityManager entityManager) {
		this.cursodao = new CursoDAOImpl(entityManager);
	}

	public void insert(Curso t) throws Exception {
		cursodao.insert(t);

	}

	public void update(Curso t) throws Exception {
		cursodao.update(t);

	}

	public void delete(Curso t) throws Exception {
		cursodao.delete(t);

	}

	public Curso findById(Integer id) throws Exception {
		return cursodao.findById(id);

	}

	public List<Curso> findAll() throws Exception {
		return cursodao.findAll();

	}

	public Curso findByNome(String nome) {
		return cursodao.findByNome(nome);

	}

	public List<Curso> findByCoordenador(Coordenador coordenador) {
		return cursodao.findByCoordenador(coordenador);

	}

	public List<Curso> findByDisciplina(Disciplina disciplina) {
		return cursodao.findByDisciplina(disciplina);

	}

}
