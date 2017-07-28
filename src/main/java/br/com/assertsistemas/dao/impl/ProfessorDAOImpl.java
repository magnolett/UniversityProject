package br.com.assertsistemas.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Usuario;

public class ProfessorDAOImpl extends GenericDaoImpl<Professor, Integer> implements ProfessorDAO {

	private EntityManager entityManager;

	public ProfessorDAOImpl(EntityManager entityManager) {
		super(entityManager, Professor.class);
		this.entityManager = entityManager;
	}

	private static final long serialVersionUID = 616879481825448941L;

	public List<Professor> findByQualificacao(String qualificacao) {

		return entityManager.createNamedQuery("Professor.findByQualificacao", Professor.class)
				.setParameter("qualificacao", qualificacao).getResultList();
	}

	public Professor findByDisciplina(Disciplina disciplina) {

		return entityManager.createNamedQuery("Professor.findByDisciplina", Professor.class)
				.setParameter("disciplina", disciplina).getSingleResult();
	}

	public Professor findByUsuario(Usuario usuario) {

		return entityManager.createNamedQuery("Professor.findByUsuario", Professor.class)
				.setParameter("usuario", usuario).getSingleResult();
	}

	public static void main(String[] args) throws Exception {
		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();

		Professor p = new ProfessorDAOImpl(entityManager)
				.findByUsuario(new UsuarioDAOImpl(entityManager).findAll().get(1));
		System.out.println(p.getUsuario());

	}

}

// <---- Anotações ---->
// List<Professor> p = new
// ProfessorDAOImpl(entityManager).findByQualificacao("DOUTOR");
// List<Professor> p = new
// ProfessorDAOImpl(entityManager).findByDisciplina(new
// DisciplinaDAOImpl(entityManager).findAll().get(0));