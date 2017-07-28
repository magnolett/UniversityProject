package br.com.assertsistemas.view.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.service.DesempenhoService;
import br.com.assertsistemas.service.impl.DesempenhoServiceImpl;
import br.com.assertsistemas.view.AlunoController;
import br.com.assertsistemas.view.LoginController;

public class AlunoMenuControllerImpl implements AlunoController {

	private DesempenhoService desempenhoservice;

	public AlunoMenuControllerImpl(EntityManager entityManager) {
		this.desempenhoservice = new DesempenhoServiceImpl(entityManager);
	}

	@Override
	public void draw(Object... objects) {
		alunoScreen((Aluno) objects[0]);
	}

	@Override
	public void alunoScreen(Aluno aluno) {

		boolean repeat = true;
		do {

			String option = JOptionPane.showInputDialog("1. Consultar Notas || 5. Deslogar || 9. Encerrar o programa");

			int a = 0;

			try {
				a = Integer.valueOf(option);
			} catch (Exception e) {
			}

			if (a == 1) {

				List<Desempenho> desempenhos = desempenhoservice.findByAluno(aluno);
				String resultado = "Disciplina | Nota 1 | Nota 2 | Nota 3 | Média Nota | Status \n";
				for (Desempenho desempenho : desempenhos) {
					resultado += desempenho.getDisciplina().getNome() + " | " + desempenho.getNota1() + " | "
							+ desempenho.getNota2() + " | " + desempenho.getNota3() + " | " + desempenho.getMedianota()
							+ " | " + desempenho.getStatus().getNome() + "\n";
				}

				JOptionPane.showMessageDialog(null, resultado);
			} else if (a == 5) {
				EntityManager em = Persistence.createEntityManagerFactory("teste01").createEntityManager();
				JOptionPane.showMessageDialog(null, "Voltando ao menu de login");
				LoginController login = new LoginControllerImpl(em);
				login.loginScreen();
				repeat = false;
			} else {
				JOptionPane.showMessageDialog(null, "Número inválido!");
			}
			if (a == 9) {
				JOptionPane.showMessageDialog(null, "Programa encerrado com sucesso.");
				System.exit(0);
			}

		} while (repeat);

	}
}
