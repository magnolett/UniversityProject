package br.com.assertsistemas.dao.impl;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.entity.Usuario;

public class UsuarioDAOImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDAO {

	private EntityManager entityManager;

	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager, Usuario.class);
		this.entityManager = entityManager;
	}

	private static final long serialVersionUID = 2376291903323865114L;

	public Usuario findByLoginAndSenha(String login, String senha) {

		return entityManager.createNamedQuery("Usuario.findByLoginAndSenha", Usuario.class).setParameter("login", login)
				.setParameter("senha", senha).getSingleResult();

	}
}
