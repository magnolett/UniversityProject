package br.com.assertsistemas.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Usuario;

public class CoordenadorDAOImpl extends GenericDaoImpl<Coordenador, Integer> implements CoordenadorDAO {

	private EntityManager entityManager;

	public CoordenadorDAOImpl(EntityManager entityManager) {
		super(entityManager, Coordenador.class);
		this.entityManager = entityManager;
	}

	private static final long serialVersionUID = 1276284675274766374L;

	public Coordenador findByUsuario(Usuario usuario) {

		return entityManager.createNamedQuery("Coordenador.findByUsuario", Coordenador.class)
				.setParameter("usuario", usuario).getSingleResult();
	}

	public static void main(String[] args) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("teste01");
		EntityManager entityManager = factory.createEntityManager();
		Coordenador coordenador = new CoordenadorDAOImpl(entityManager)
				.findByUsuario(new UsuarioDAOImpl(entityManager).findAll().get(0));
		System.out.println(coordenador.getNome());
	}
}

// TO DO: SingleResult não retorna nada.