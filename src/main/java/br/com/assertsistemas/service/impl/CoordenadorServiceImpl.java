package br.com.assertsistemas.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.dao.impl.CoordenadorDAOImpl;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.CoordenadorService;

public class CoordenadorServiceImpl implements CoordenadorService {

	private CoordenadorDAO coordenadordao;

	private static final long serialVersionUID = 7625962055177815198L;

	public CoordenadorServiceImpl(EntityManager entityManager) {
		this.coordenadordao = new CoordenadorDAOImpl(entityManager);
	}

	public void insert(Coordenador t) throws Exception {
		coordenadordao.insert(t);
	}

	public void update(Coordenador t) throws Exception {
		coordenadordao.update(t);
	}

	public void delete(Coordenador t) throws Exception {
		coordenadordao.delete(t);
	}

	public Coordenador findById(Integer id) throws Exception {
		return coordenadordao.findById(id);
	}

	public List<Coordenador> findAll() throws Exception {
		return coordenadordao.findAll();
	}

	public Coordenador findByUsuario(Usuario usuario) {
		return coordenadordao.findByUsuario(usuario);
	}

}
