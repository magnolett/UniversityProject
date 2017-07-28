package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.dao.impl.ProfessorDAOImpl;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorDAO professordao;

	private static final long serialVersionUID = -6055377854645728377L;

	public ProfessorServiceImpl(EntityManager entityManager) {
		this.professordao = new ProfessorDAOImpl(entityManager);

	}

	public void insert(Professor t) throws Exception {
		professordao.insert(t);

	}

	public void update(Professor t) throws Exception {
		professordao.update(t);

	}

	public void delete(Professor t) throws Exception {
		professordao.delete(t);

	}

	public Professor findById(Integer id) throws Exception {
		return professordao.findById(id);

	}

	public List<Professor> findAll() throws Exception {
		return professordao.findAll();

	}

	public List<Professor> findByQualificacao(String qualificacao) {
		return professordao.findByQualificacao(qualificacao);

	}

	public Professor findByDisciplina(Disciplina disciplina) {
		return professordao.findByDisciplina(disciplina);

	}

	public Professor findByUsuario(Usuario usuario) {
		return professordao.findByUsuario(usuario);

	}

}
