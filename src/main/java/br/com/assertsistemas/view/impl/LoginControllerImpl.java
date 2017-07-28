package br.com.assertsistemas.view.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Pessoa;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.service.UsuarioService;
import br.com.assertsistemas.service.impl.UsuarioServiceImpl;
import br.com.assertsistemas.view.AlunoController;
import br.com.assertsistemas.view.CoordenadorController;
import br.com.assertsistemas.view.LoginController;
import br.com.assertsistemas.view.ProfessorController;

public class LoginControllerImpl implements LoginController {

	private UsuarioService usuarioservice;
	private AlunoController alunocontroller;
	private ProfessorController professorcontroller;
	private CoordenadorController coordenadorcontroller;

	public LoginControllerImpl(EntityManager entityManager) {
		usuarioservice = new UsuarioServiceImpl(entityManager);
		alunocontroller = new AlunoMenuControllerImpl(entityManager);
		professorcontroller = new ProfessorMenuControllerImpl(entityManager);
		coordenadorcontroller = new CoordenadorMenuControllerImpl(entityManager);
	}

	@Override
	public void draw(Object... objects) {
		loginScreen();
	}

	@Override
	public void loginScreen() {

		int tentativa = 0;
		do {
			String login = JOptionPane.showInputDialog("Digite seu login");
			String senha = JOptionPane.showInputDialog("Digite sua senha");
			Pessoa pessoa = usuarioservice.autentica(login, senha);

			if (pessoa instanceof Aluno) {
				Aluno aluno = (Aluno) pessoa;
				alunocontroller.draw(aluno);
				tentativa = 4;
			} else if (pessoa instanceof Coordenador) {
				Coordenador coordenador = (Coordenador) pessoa;
				coordenadorcontroller.draw(coordenador);
				tentativa = 4;
			} else if (pessoa instanceof Professor) {
				Professor professor = (Professor) pessoa;
				professorcontroller.draw(professor);
				tentativa = 4;
			} else {
				JOptionPane.showMessageDialog(null, "Tente novamente", "Erro!", JOptionPane.ERROR_MESSAGE);
				tentativa++;
			}

		} while (tentativa < 3);

	}


	public static void main(String[] args) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		LoginController controller = new LoginControllerImpl(entityManager);
		controller.loginScreen();
	}
}


// <---- Anotações ---->

// if (login.equals(login) && senha.equals(senha)) {
