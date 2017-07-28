package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.mysql.jdbc.SQLError;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.dao.impl.AlunoDAOImpl;
import br.com.assertsistemas.dao.impl.CoordenadorDAOImpl;
import br.com.assertsistemas.dao.impl.ProfessorDAOImpl;
import br.com.assertsistemas.dao.impl.UsuarioDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Pessoa;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDAO usuariodao;
	private AlunoDAO alunodao;
	private ProfessorDAO professordao;
	private CoordenadorDAO coordenadordao;

	private static final long serialVersionUID = -6527155138116290281L;

	public UsuarioServiceImpl(EntityManager entityManager) {
		this.usuariodao = new UsuarioDAOImpl(entityManager);
		this.alunodao = new AlunoDAOImpl(entityManager);
		this.professordao = new ProfessorDAOImpl(entityManager);
		this.coordenadordao = new CoordenadorDAOImpl(entityManager);

	}

	public void insert(Usuario t) throws Exception {
		usuariodao.insert(t);

	}

	public void update(Usuario t) throws Exception {
		usuariodao.update(t);

	}

	public void delete(Usuario t) throws Exception {
		usuariodao.delete(t);

	}

	public Usuario findById(Integer id) throws Exception {
		return usuariodao.findById(id);

	}

	public List<Usuario> findAll() throws Exception {
		return usuariodao.findAll();

	}

	public Pessoa autentica(String login, String senha) {

		try {
			Usuario usuario = usuariodao.findByLoginAndSenha(login, senha);

			if (usuario != null) {

				switch (usuario.getTipoUsuario()) {
				case PROFESSOR:
					return professordao.findByUsuario(usuario);
				case ALUNO:
					return alunodao.findByUsuario(usuario);
				case COORDENADOR:
					return coordenadordao.findByUsuario(usuario);
				default:
					return null;

				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void teste() {
		Pessoa a = new Pessoa() {

		};

		if (a instanceof Professor) {

		} else if (a instanceof Aluno) {

		}

	}
}
