package br.com.assertsistemas.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.assertsistemas.dao.DesempenhoDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Status;

public class DesempenhoDAOImpl extends GenericDaoImpl<Desempenho, Integer> implements DesempenhoDAO {

	private EntityManager entityManager;

	public DesempenhoDAOImpl(EntityManager entityManager) {
		super(entityManager, Desempenho.class);
		this.entityManager = entityManager;
	}

	private static final long serialVersionUID = -5406254612373046203L;

	public List<Desempenho> findByAluno(Aluno aluno) {

		return entityManager.createNamedQuery("Desempenho.findByAluno", Desempenho.class).setParameter("aluno", aluno)
				.getResultList();
	}

	public List<Desempenho> findByDisciplina(Disciplina disciplina) {

		return entityManager.createNamedQuery("Desempenho.findByDisciplina", Desempenho.class)
				.setParameter("disciplina", disciplina).getResultList();
	}

	public Desempenho findByAlunoandDisciplina(Aluno aluno, Disciplina disciplina) {

		return entityManager.createNamedQuery("Desempenho.findByAlunoAndDisciplina", Desempenho.class)
				.setParameter("aluno", aluno).setParameter("disciplina", disciplina).getSingleResult();
	}

	public List<Desempenho> findByStatus(Status status) {

		return entityManager.createNamedQuery("Desempenho.findByStatus", Desempenho.class)
				.setParameter("status", status).getResultList();
	}

	public List<Desempenho> findByStatusAndCoordenador(Status status, Coordenador coordenador) {

		return entityManager
				.createQuery(
						"SELECT d FROM Desempenho as d JOIN FETCH d.disciplina.curso.coordenador WHERE d.status = :status and d.disciplina.curso.coordenador = :coordenador",
						Desempenho.class)
				.setParameter("status", status).setParameter("coordenador", coordenador).getResultList();
	}

	public static void main(String[] args) throws Exception {
		Aluno aluno = new Aluno();
		aluno.setNome("BUCETA");
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("VACA");
		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();

		Desempenho desempenho = new DesempenhoDAOImpl(entityManager).findByAlunoandDisciplina(new Aluno(),
				new Disciplina());
		System.out.println(desempenho);
	}
}

// <---- Anotações ---->
// Desempenho d = new DesempenhoDAOImpl(entityManager).findByAluno(new
// AlunoDAOImpl(entityManager).findAll().get(0));
// Desempenho d = new
// DesempenhoDAOImpl(entityManager).findByDisciplina(new
// DisciplinaDAOImpl(entityManager).findAll().get(0));
