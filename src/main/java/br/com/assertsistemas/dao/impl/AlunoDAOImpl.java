package br.com.assertsistemas.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Usuario;

public class AlunoDAOImpl extends GenericDaoImpl<Aluno, Integer> implements AlunoDAO {

	private EntityManager entityManager;

	public AlunoDAOImpl(EntityManager entityManager) {
		super(entityManager, Aluno.class);
		this.entityManager = entityManager;
	}

	private static final long serialVersionUID = -3600395544045796017L;

	public Aluno findByUsuario(Usuario usuario) {

		return entityManager.createNamedQuery("Aluno.findByUsuario", Aluno.class).setParameter("usuario", usuario)
				.getSingleResult();

	}

	public Aluno findByMatricula(long matricula) {

		return entityManager.createNamedQuery("Aluno.findByMatricula", Aluno.class).setParameter("matricula", matricula)
				.getSingleResult();

	}

	public List<Aluno> findByDisciplina(Disciplina disciplina) {

		return entityManager.createNamedQuery("Aluno.findByDisciplina", Aluno.class)
				.setParameter("disciplinas", disciplina).getResultList();
	}

}
