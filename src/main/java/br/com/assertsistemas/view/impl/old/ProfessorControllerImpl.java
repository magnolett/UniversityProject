package br.com.assertsistemas.view.impl.old;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.dao.impl.ProfessorDAOImpl;
import br.com.assertsistemas.dao.impl.UsuarioDAOImpl;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.util.Validacao;
import br.com.assertsistemas.view.ProfessorController;

public class ProfessorControllerImpl implements ProfessorController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3519163022347503922L;

	
		@Override
		public Professor cadastroProfessor() throws Exception {
		Professor professor = new Professor();
		Validacao valid = new Validacao();
		Usuario usuario = new Usuario();
		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		ProfessorDAO professordao = new ProfessorDAOImpl(entityManager);

		usuario.setTipoUsuario(Tipo.PROFESSOR);

		String nomeProfessor = JOptionPane.showInputDialog("Digite o nome para cadastro:");
		if (valid.validNomeSobrenome(nomeProfessor)) {
			professor.setNome(nomeProfessor);
		} else {
			professor.setNome(valid.repeticao("nome"));
		}
		String sexo = JOptionPane.showInputDialog("Digite o gênero sexual (SOMENTE M/F):");
		if (valid.validChar(sexo)) {
			professor.setSexo(sexo.toString().charAt(0));
		} else {
			professor.setSexo(valid.repeticao("sexo").charAt(0));
		}
		String idade = JOptionPane.showInputDialog("Digite a idade:");
		if (valid.validIntLong(idade)) {
			int idade1 = Integer.valueOf(idade);
			professor.setIdade(idade1);
		} else {
			String a = JOptionPane.showInputDialog("Digite somente números inteiros!");
			int idade2 = Integer.valueOf(a);
			professor.setIdade(idade2);
		}
		String qualificacao = JOptionPane.showInputDialog("Digite o maior título (qualificação - doutor, mestre, etc)");
		if (valid.validNomeSobrenome(qualificacao)) {
			professor.setQualificacao(qualificacao);
		} else {
			professor.setQualificacao((valid.repeticao("qualificacao").trim()));
		}
		String login = JOptionPane.showInputDialog("Insira um login para cadastro:");
		usuario.setLogin(login);

		String senha = JOptionPane.showInputDialog("Cadastre uma senha para o seu login:");
		usuario.setSenha(senha);

		UsuarioDAO usuariodao = new UsuarioDAOImpl(entityManager);
		usuariodao.insert(usuario);
		professor.setUsuario(usuario);

		professordao.insert(professor);
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		return professor;

	}


		@Override
		public void draw() {
			// TODO Auto-generated method stub
			
		}
}