package br.com.assertsistemas.main;

import javax.persistence.EntityManager;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.service.DesempenhoService;
import br.com.assertsistemas.service.impl.DesempenhoServiceImpl;

public class MainCoordenador {

	private DesempenhoService desempenhoservice;
	
	public MainCoordenador (EntityManager entityManager, Coordenador coordenador) {
		
		this.desempenhoservice = new DesempenhoServiceImpl(entityManager);

	}
	
		public Coordenador menuCoordenador (EntityManager entityManager, Coordenador coordenador) {
			return coordenador;
		}
	}
