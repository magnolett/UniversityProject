package br.com.assertsistemas.view.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.assertsistemas.dao.impl.AlunoDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.service.AlunoService;
import br.com.assertsistemas.service.DesempenhoService;
import br.com.assertsistemas.service.DisciplinaService;
import br.com.assertsistemas.service.impl.AlunoServiceImpl;
import br.com.assertsistemas.service.impl.DesempenhoServiceImpl;
import br.com.assertsistemas.service.impl.DisciplinaServiceImpl;
import br.com.assertsistemas.service.impl.ProfessorServiceImpl;
import br.com.assertsistemas.view.LoginController;
import br.com.assertsistemas.view.ProfessorController;

public class ProfessorMenuControllerImpl implements ProfessorController {

	private DisciplinaService disciplinaservice;
	private AlunoService alunoservice;
	private DesempenhoService desempenhoservice;
	private Aluno aluno;
	private Disciplina disciplina;
	private Desempenho desempenho;

	public ProfessorMenuControllerImpl(EntityManager entityManager) {
		new ProfessorServiceImpl(entityManager);
		disciplinaservice = new DisciplinaServiceImpl(entityManager);
		alunoservice = new AlunoServiceImpl(entityManager);
		new AlunoDAOImpl(entityManager);
		desempenhoservice = new DesempenhoServiceImpl(entityManager);
		new Professor();
		aluno = new Aluno();
		disciplina = new Disciplina();

	}

	@Override
	public void draw(Object... objects) {
		menu((Professor) objects[0]);

	}

	public void cadastrarNotas(Professor professor) {
		desempenho = new Desempenho();
		List<Disciplina> disciplinas = disciplinaservice.findByProfessor(professor);
		disciplinas.forEach((dis) -> {
			JOptionPane.showMessageDialog(null, "Disciplina: \n" + dis.getId() + " - " + dis.getNome());
		});

		if (desempenho != null) {
			try {
				String choice = JOptionPane.showInputDialog("Digite o ID da disciplina: ");
				int idd = Integer.valueOf(choice);
				disciplina = disciplinaservice.findById(idd);
				System.out.println(disciplina.getNome());
				desempenho.setDisciplina(disciplina);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Disciplina não encontrada, tente novamente");
				cadastrarNotas(professor);
				e.printStackTrace();
			}

			try {
				String csaluno = JOptionPane
						.showInputDialog("2. Consultar alunos (anote o id) || 9. Voltar ao menu anterior");
				int csal = Integer.valueOf(csaluno);
				if (csal == 2) {
					consultarAlunos(professor);
				} else if (csal == 9) {
					menu(professor);
				} else {
					JOptionPane.showMessageDialog(null, "Tecla incorreta!");
					cadastrarNotas(professor);
				}
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null, "Tecla incorreta!");
				cadastrarNotas(professor);
				ee.printStackTrace();
			}
			try {
				String idaluno = JOptionPane.showInputDialog("Insira o id do aluno: ");
				int id = Integer.valueOf(idaluno);
				aluno = alunoservice.findById(id);
				System.out.println(aluno);
				desempenho.setAluno(aluno);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado, tente novamente");
				e.printStackTrace();
			}
			System.out.println(desempenho.getAluno());
			System.out.println(desempenho.getDisciplina());
			Nota1();
			System.out.println(desempenho.getNota1());
			Nota2();
			System.out.println(desempenho.getNota2());
			Nota3();
			System.out.println(desempenho.getNota3());
			JOptionPane.showMessageDialog(null, "Notas cadastradas com sucesso na disciplina: " + disciplina.getNome()
					+ " - para o aluno: " + aluno.getNome());
			try {
				desempenhoservice.insert(desempenho);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro, tente novamente!");
				e.printStackTrace();
			}
		} else {
			System.exit(0);
		}
	}

	public void alterarNotas(Professor professor) {
		desempenho = new Desempenho();
		List<Disciplina> disciplinas = disciplinaservice.findByProfessor(professor);
		disciplinas.forEach((dis) -> {
			JOptionPane.showMessageDialog(null, "Disciplina: \n" + dis.getId() + " - " + dis.getNome());
		});
		try {
			String choice = JOptionPane.showInputDialog("Digite o ID da disciplina: ");
			int idd = Integer.valueOf(choice);
			disciplina = disciplinaservice.findById(idd);
			System.out.println(disciplina.getNome());
			desempenho.setDisciplina(disciplina);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Disciplina não encontrada, tente novamente");
			alterarNotas(professor);
			e.printStackTrace();
		}
		try {
			String csaluno = JOptionPane
					.showInputDialog("2. Consultar alunos (anote o id) || 9. Voltar ao menu anterior");
			int csal = Integer.valueOf(csaluno);
			if (csal == 2) {
				consultarAlunos(professor);
			} else if (csal == 9) {
				menu(professor);
			} else {
				JOptionPane.showMessageDialog(null, "Tecla incorreta!");
				alterarNotas(professor);
			}
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(null, "Tecla incorreta!");
			alterarNotas(professor);
			ee.printStackTrace();
		}
		try {
			String idaluno = JOptionPane.showInputDialog("Insira o id do aluno: ");
			int id = Integer.valueOf(idaluno);
			aluno = alunoservice.findById(id);
			desempenho = desempenhoservice.findByAlunoandDisciplina(aluno, disciplina);
			desempenho.setAluno(aluno);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aluno não encontrado, tente novamente");
			e.printStackTrace();
			alterarNotas(professor);
		}

		JOptionPane.showMessageDialog(null,
				"Aluno: " + aluno.getNome() + " || " + "Primeira Nota : " + desempenho.getNota1() + " || "
						+ "Segunda Nota : " + desempenho.getNota2() + " || " + "Terceira Nota : "
						+ desempenho.getNota3());

		try {
			String option = JOptionPane.showInputDialog(
					"1. Alterar primeira nota. || 2. Alterar segunda nota. || 3. Alterar terceira nota. || 9. Voltar ao menu inicial");
			int opcao = Integer.valueOf(option);
			if (opcao == 1) {
				String nota1 = JOptionPane.showInputDialog("Digite a primeira nota: ");
				double n1 = Double.valueOf(nota1);
				desempenho.setNota1(n1);
				desempenhoservice.update(desempenho);
				JOptionPane.showMessageDialog(null,
						"Nota inserida com sucesso! A primeira nota agora é :" + desempenho.getNota1());

			}
			if (opcao == 2) {
				String nota2 = JOptionPane.showInputDialog("Digite a segunda nota: ");
				double n2 = Double.valueOf(nota2);
				desempenho.setNota2(n2);
				desempenhoservice.update(desempenho);
				JOptionPane.showMessageDialog(null,
						"Nota inserida com sucesso! A segunda nota agora é :" + desempenho.getNota2());
			}
			if (opcao == 3) {
				String nota3 = JOptionPane.showInputDialog("Digite a nota: ");
				double n3 = Double.valueOf(nota3);
				desempenho.setNota3(n3);
				desempenhoservice.update(desempenho);
				JOptionPane.showMessageDialog(null,
						"Nota inserida com sucesso! A terceira nota agora é :" + desempenho.getNota3());

			}
			JOptionPane.showMessageDialog(null, "Voltando ao menu inicial...");
			menu(professor);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente números! (MODELO 0.0 | EXEMPLO 7.5)");
			e.printStackTrace();
			alterarNotas(professor);
		}
	}

	public void consultarAlunos(Professor professor) {
		List<Disciplina> disciplinas = disciplinaservice.findByProfessor(professor);
		disciplinas.forEach((d) -> {
			System.out.println("-----------------");
			System.out.println(d.getAlunos().get(0));
			System.out.println("-----------------");
			for (Aluno a : d.getAlunos()) {
				JOptionPane.showMessageDialog(null, "ID: " + a.getId() + "||" + " Nome: " + a.getNome() + "||"
						+ " Matrícula: " + a.getMatricula() + "||" + " Semestre: " + a.getSemestre());
			}
		});
	}

	public void menu(Professor professor) {

		boolean repeticao = true;

		do {

			String opcao = JOptionPane.showInputDialog(
					"1. Cadastrar notas || 2. Alterar notas || 3. Consultar alunos || 5. Deslogar || 9. Encerrar o programa");

			int a = 0;

			try {
				a = Integer.valueOf(opcao);

				if (a == 3) {
					consultarAlunos(professor);
				} else if (a == 5) {
					EntityManager em = Persistence.createEntityManagerFactory("teste01").createEntityManager();
					JOptionPane.showMessageDialog(null, "Voltando ao menu de login");
					LoginController login = new LoginControllerImpl(em);
					login.loginScreen();
					repeticao = false;
				} else if (a == 1) {
					cadastrarNotas(professor);
				} else if (a == 2) {
					alterarNotas(professor);
				}
				if (a == 9) {
					JOptionPane.showMessageDialog(null, "Programa encerrado com sucesso.");
					System.exit(0);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opção incorreta, tente novamente!");
				menu(professor);
			}
		} while (repeticao);
	}

	public double Nota1() {
		double n1 = 5;
		try {
			String nota1 = JOptionPane.showInputDialog("Insira a primeira nota: ");
			n1 = Double.valueOf(nota1);
			desempenho.setNota1(n1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente números! (MODELO 0.0 | EXEMPLO 7.5)");
			Nota1();
		}
		return n1;
	}

	public double Nota2() {
		double n2 = 5;
		try {
			String nota2 = JOptionPane.showInputDialog("Insira a segunda nota: ");
			n2 = Double.valueOf(nota2);
			desempenho.setNota2(n2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente números! (MODELO 0.0 | EXEMPLO 7.5)");
			Nota2();
		}
		return n2;
	}

	public double Nota3() {
		double n3 = 5;
		try {
			String nota3 = JOptionPane.showInputDialog("Insira a terceira nota: ");
			n3 = Double.valueOf(nota3);
			desempenho.setNota3(n3);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente números! (MODELO 0.0 | EXEMPLO 7.5)");
			Nota3();
		}
		return n3;
	}
}
