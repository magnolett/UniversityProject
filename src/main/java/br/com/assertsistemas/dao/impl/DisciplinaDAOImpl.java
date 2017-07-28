package br.com.assertsistemas.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.assertsistemas.dao.DisciplinaDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.service.impl.ProfessorServiceImpl;

public class DisciplinaDAOImpl extends GenericDaoImpl<Disciplina, Integer> implements DisciplinaDAO {

	private EntityManager entityManager;

	private static final long serialVersionUID = 1788663380649844391L;

	public DisciplinaDAOImpl(EntityManager entityManager) {
		super(entityManager, Disciplina.class);
		this.entityManager = entityManager;
	}

	public Disciplina findByCodigo(long codigo) {

		return entityManager.createNamedQuery("Disciplina.findByCodigo", Disciplina.class)
				.setParameter("codigo", codigo).getSingleResult();
	}

	public List<Disciplina> findByProfessor(Professor professor) {

		return entityManager.createNamedQuery("Disciplina.findByProfessor", Disciplina.class)
				.setParameter("professor", professor).getResultList();
	}

	public List<Disciplina> findByCurso(Curso curso) {

		return entityManager.createNamedQuery("Disciplina.findByCurso", Disciplina.class).setParameter("curso", curso)
				.getResultList();
	}

	public List<Disciplina> findByAluno(Aluno aluno) {

		return entityManager.createNamedQuery("Disciplina.findByAluno", Disciplina.class).setParameter("aluno", aluno)
				.getResultList();
	}

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		DisciplinaDAO dis = new DisciplinaDAOImpl(em);
		try {
			dis.findByProfessor(new ProfessorServiceImpl(em).findById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
