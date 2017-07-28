package br.com.assertsistemas.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.assertsistemas.dao.CursoDAO;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;

public class CursoDAOImpl extends GenericDaoImpl<Curso, Integer> implements CursoDAO {

	private EntityManager entityManager;

	public CursoDAOImpl(EntityManager entityManager) {
		super(entityManager, Curso.class);
		this.entityManager = entityManager;
	}

	private static final long serialVersionUID = -3716861003677761175L;

	public Curso findByNome(String nome) {

		return entityManager.createNamedQuery("Curso.findByNome", Curso.class).setParameter("nome", nome)
				.getSingleResult();

	}

	public List<Curso> findByCoordenador(Coordenador coordenador) {

		return entityManager.createNamedQuery("Curso.findByCoordenador", Curso.class)
				.setParameter("coordenador", coordenador).getResultList();
	}

	public List<Curso> findByDisciplina(Disciplina disciplina) {

		return entityManager.createNamedQuery("Curso.findByDisciplina", Curso.class)
				.setParameter("disciplina", disciplina).getResultList();

	}

	public static void main(String[] args) throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		List<Curso> c = new CursoDAOImpl(entityManager).findAll();
		System.out.println(c);
	}
}

// <---- Anotações ---->
// Curso c = new CursoDAOImpl(entityManager).findByNome("Sistemas de
// Informação");
// Curso c = new CursoDAOImpl(entityManager).findByCoordenador(new
// CoordenadorDAOImpl(entityManager).findAll().get(0));
// Curso c = new CursoDAOImpl(entityManager).findByDisciplina(new
// DisciplinaDAOImpl(entityManager).findAll().get(0));
