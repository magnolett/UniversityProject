import java.util.List;

import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;

Disciplina disciplina = null;
		Aluno aluno = null;
		//

		List<Disciplina> disciplinas = disciplinaservice.findByProfessor(professor);
		disciplinas.forEach((dis) -> {
			JOptionPane.showMessageDialog(null, "Disciplina: \n" + dis.getId() + " - " + dis.getNome());
		});

		try {
			String choice = JOptionPane.showInputDialog("Digite o ID da disciplina: ");
			int idd = Integer.valueOf(choice);
			disciplina = disciplinaservice.findById(idd);
			System.out.println(disciplina.getNome());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Disciplina não encontrada, tente novamente");
			cadastrarNotas(professor);
			e.printStackTrace();
		}

		try {
			String confirm = JOptionPane.showInputDialog("Confirma a disciplina? S/N: -> " + disciplina.getNome());
			if (confirm.equals("S") || confirm.equals("s")) {
				JOptionPane.showMessageDialog(null, "Consulte os alunos para cadastrar notas pelo ID.");
				String idaluno = JOptionPane.showInputDialog("Insira o id do aluno: ");
				int id = Integer.valueOf(idaluno);
				aluno = alunoservice.findById(id);
				System.out.println(aluno);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aluno não encontrado, tente novamente");
			e.printStackTrace();
		}

		Desempenho desempenho = new Desempenho();

		try {
			String confirma = JOptionPane.showInputDialog("Confirma o nome do aluno? S/N: -> " + aluno.getNome());
			if (confirma.equals("S") || confirma.equals("s")) {
				String nota1 = JOptionPane.showInputDialog("Insira a primeira nota: ");
				double n1 = Double.valueOf(nota1);
				desempenho.setNota1(n1);
			} else if (confirma.equals("N") || confirma.equals("n")) {
				JOptionPane.showMessageDialog(null, "Retornando ao Menu");
				cadastrarNotas(professor);
			} else if (confirma.matches("[a-z]+")) {
				JOptionPane.showMessageDialog(null, "Incorreto, tente novamente!");
				cadastrarNotas(professor);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente números! (MODELO 0.0 | EXEMPLO 7.5)");
		}
		try {
			String nota2 = JOptionPane.showInputDialog("Insira a segunda nota: ");
			double n2 = Double.valueOf(nota2);
			desempenho.setNota2(n2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente números! (MODELO 0.0 | EXEMPLO 7.5)");
		}
		try {
			String nota3 = JOptionPane.showInputDialog("Insira a terceira nota: ");
			double n3 = Double.valueOf(nota3);
			desempenho.setNota3(n3);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente núemros! (MODELO 0.0 | EXEMPLO 7.5)");
			desempenho.setAluno(aluno);
			desempenho.setDisciplina(disciplina);
		}
		try {
			desempenhoservice.insert(desempenho);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// desempenho.setAluno();
