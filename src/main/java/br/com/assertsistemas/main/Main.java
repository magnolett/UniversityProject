package br.com.assertsistemas.main;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.assertsistemas.view.LoginController;
import br.com.assertsistemas.view.impl.LoginControllerImpl;

public class Main {

	private EntityManager entityManager;
	private LoginController loginController;
	
	public Main() {
		
		entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		loginController = new LoginControllerImpl(entityManager);
		loginController.draw();
	}
	
	
		public static void main(String[] args) {
			new Main();
		}
}
