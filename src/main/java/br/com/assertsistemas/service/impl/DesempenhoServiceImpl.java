package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.DesempenhoDAO;
import br.com.assertsistemas.dao.impl.DesempenhoDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Status;
import br.com.assertsistemas.service.DesempenhoService;

public class DesempenhoServiceImpl implements DesempenhoService {

	private DesempenhoDAO desempenhodao;

	private static final long serialVersionUID = -9064224896686001268L;

	public DesempenhoServiceImpl(EntityManager entityManager) {
		this.desempenhodao = new DesempenhoDAOImpl(entityManager);

	}

	public void insert(Desempenho t) throws Exception {
		desempenhodao.insert(t);

	}

	public void update(Desempenho t) throws Exception {
		desempenhodao.update(t);

	}

	public void delete(Desempenho t) throws Exception {
		desempenhodao.delete(t);

	}

	public Desempenho findById(Integer id) throws Exception {
		return desempenhodao.findById(id);

	}

	public List<Desempenho> findAll() throws Exception {
		return desempenhodao.findAll();

	}

	public List<Desempenho> findByAluno(Aluno aluno) {
		return desempenhodao.findByAluno(aluno);

	}

	public List<Desempenho> findByDisciplina(Disciplina disciplina) {
		return desempenhodao.findByDisciplina(disciplina);

	}

	public List<Desempenho> findByStatusAndCoordenador(Status status, Coordenador coordenador) {
		return desempenhodao.findByStatusAndCoordenador(status, coordenador);

	}

	public Desempenho findByAlunoandDisciplina(Aluno aluno, Disciplina disciplina) {
		return desempenhodao.findByAlunoandDisciplina(aluno, disciplina);
	}

	public double updateMediaNotas(Desempenho desempenho, EntityManager entityManager) throws Exception {
		DesempenhoService srv = new DesempenhoServiceImpl(entityManager);
		double n1 = desempenho.getNota1();
		double n2 = desempenho.getNota2();
		double n3 = desempenho.getNota3();
		double mediaNota = (n1 + n2 + n3) / 3;
		desempenho.setMedianota(mediaNota);
		srv.resultadoDesempenho(desempenho);
		desempenhodao.update(desempenho);
		return mediaNota;
	}

	public Status resultadoDesempenho(Desempenho desempenho) {

		if ((desempenho.getNota1() == 0) || (desempenho.getNota2() == 0) || (desempenho.getNota3() == 0)) {
			return Status.EM_ANDAMENTO;
		}
		if (desempenho.getMedianota() > 6) {
			return Status.APROVADO;

		} else if ((desempenho.getMedianota() > 5) && (desempenho.getMedianota() < 6)) {
			return Status.PENDENTE;

		} else if (desempenho.getMedianota() < 5) {
			return Status.REPROVADO;
		}

		return null;
	}

}
