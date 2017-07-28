package br.com.assertsistemas.view.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.dao.impl.UsuarioDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.AlunoService;
import br.com.assertsistemas.service.DisciplinaService;
import br.com.assertsistemas.service.ProfessorService;
import br.com.assertsistemas.service.UsuarioService;
import br.com.assertsistemas.service.impl.AlunoServiceImpl;
import br.com.assertsistemas.service.impl.DisciplinaServiceImpl;
import br.com.assertsistemas.service.impl.ProfessorServiceImpl;
import br.com.assertsistemas.service.impl.UsuarioServiceImpl;
import br.com.assertsistemas.util.Validacao;
import br.com.assertsistemas.view.CoordenadorController;
import br.com.assertsistemas.view.LoginController;

public class CoordenadorMenuControllerImpl implements CoordenadorController {

	private EntityManager entityManager;
	private Validacao valid;
	private Disciplina disciplina;
	private Professor professor;
	private AlunoService alunoservice;
	private ProfessorService professorservice;
	private DisciplinaService disciplinaservice;

	public CoordenadorMenuControllerImpl(EntityManager entityManager) {
		this.valid = new Validacao();
		this.disciplina = new Disciplina();
		this.entityManager = entityManager;
		entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		this.professor = new Professor();
		this.alunoservice = new AlunoServiceImpl(entityManager);
		this.professorservice = new ProfessorServiceImpl(entityManager);
		this.disciplinaservice = new DisciplinaServiceImpl(entityManager);
	}

	@Override
	public void draw(Object... objects) {
		menu((Coordenador) objects[0]);
	}

	public Aluno cadastroAluno() {
		Usuario usuario = new Usuario();
		Aluno aluno = new Aluno();
		Disciplina disciplina = new Disciplina();

		String nomeAluno = JOptionPane.showInputDialog("Digite o nome para cadastro:");
		if (valid.validNomeSobrenome(nomeAluno)) {
			aluno.setNome(nomeAluno);
		} else {

			aluno.setNome(valid.repeticao("nome"));
		}
		String sexo = JOptionPane.showInputDialog("Digite o gênero sexual (SOMENTE M/F):");
		if (valid.validChar(sexo)) {
			aluno.setSexo(sexo.toString().charAt(0));
		} else {
			aluno.setSexo(valid.repeticao("sexo").charAt(0));
		}
		String idade = JOptionPane.showInputDialog("Digite a idade:");
		if (valid.validIntLong(idade)) {
			int idade1 = Integer.valueOf(idade);
			aluno.setIdade(idade1);
		} else {
			String a = JOptionPane.showInputDialog("Digite somente números inteiros!");
			int idade2 = Integer.valueOf(a);
			aluno.setIdade(idade2);
		}
		String matricula = JOptionPane.showInputDialog("Digite a matrícula");
		if (valid.validIntLong(matricula)) {
			long matr = Long.valueOf(matricula);
			aluno.setMatricula(matr);
		} else {
			aluno.setMatricula(new Long(valid.repeticao("matricula").trim()));
		}
		String semestre = JOptionPane.showInputDialog("Insira o semestre sendo cursado:");
		if (valid.validIntLong(semestre)) {
			int sem = Integer.valueOf(semestre);
			aluno.setSemestre(sem);
		} else {
			aluno.setMatricula(new Integer(valid.repeticao("semestre").trim()));
		}

		usuario.setTipoUsuario(Tipo.ALUNO);

		String login = JOptionPane.showInputDialog("Insira um login para cadastro:");
		usuario.setLogin(login);

		String senha = JOptionPane.showInputDialog("Cadastre uma senha para o seu login:");
		usuario.setSenha(senha);

		UsuarioDAO usuariodao = new UsuarioDAOImpl(entityManager);
		try {
			usuariodao.insert(usuario);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		aluno.setUsuario(usuario);
		try {
			alunoservice.insert(aluno);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

		try {
			DisciplinaService disciplinaservice = new DisciplinaServiceImpl(entityManager);

			List<Disciplina> disciplinas = disciplinaservice.findByAluno(aluno);
			disciplinas.forEach((dis) -> {
				JOptionPane.showMessageDialog(null, "Disciplina: \n" + dis.getId() + " - " + dis.getNome());
			});

			String choice = JOptionPane.showInputDialog("Digite o ID da disciplina: ");
			int idd = Integer.valueOf(choice);
			disciplina = disciplinaservice.findById(idd);
			System.out.println(disciplina.getNome());

			entityManager.getTransaction().begin();
			disciplina.getAlunos().add(aluno);
			entityManager.persist(disciplina);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Disciplina não encontrada, tente novamente");
			e.printStackTrace();
		}

		return aluno;
	}

	public static void main(String[] args) {

		Coordenador coordenador = new Coordenador();
		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		CoordenadorController controller = new CoordenadorMenuControllerImpl(entityManager);
		controller.menu(coordenador);
	}

	public void menu(Coordenador coordenador) {
		String option = JOptionPane.showInputDialog(
				"1. Cadastro Aluno || 2. Cadastro Professor || 3. Cadastro Disciplina || 5. Deslogar || 9. Encerrar aplicativo");
		int opcao = Integer.valueOf(option);
		try {
			if (opcao == 1) {
				cadastroAluno();
			}
			if (opcao == 2) {
				cadastroProfessor(professor);
			}
			if (opcao == 3) {
				cadastroDisciplina();
			}
			if (opcao == 5) {
				LoginController logincontroller = new LoginControllerImpl(entityManager);
				logincontroller.loginScreen();
			}
			if (opcao == 9) {
				JOptionPane.showMessageDialog(null, "Encerrando programa");
				System.exit(0);
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Opção inválida!");
			e.printStackTrace();
			menu(coordenador);
		}
	}

	@Override
	public Professor cadastroProfessor(Professor professor) {
		Usuario usuario = new Usuario();
		ProfessorService professorservice = new ProfessorServiceImpl(entityManager);

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

		UsuarioService usuarioservice = new UsuarioServiceImpl(entityManager);
		try {
			usuarioservice.insert(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		professor.setUsuario(usuario);

		try {
			professorservice.insert(professor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		try {
			DisciplinaService disciplinaservice = new DisciplinaServiceImpl(entityManager);

			List<Disciplina> disciplinas = disciplinaservice.findByProfessor(professor);
			disciplinas.forEach((dis) -> {
				JOptionPane.showMessageDialog(null, "Disciplina: \n" + dis.getId() + " - " + dis.getNome());
			});

			String choice = JOptionPane.showInputDialog("Digite o ID da disciplina: ");
			int idd = Integer.valueOf(choice);
			disciplina = disciplinaservice.findById(idd);
			entityManager.getTransaction().begin();
			professor.getDisciplinas().add(disciplina);
			entityManager.persist(professor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Deu erro aqui");
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
		JOptionPane.showMessageDialog(null, "SAINDO!");
		System.exit(0);
		return professor;

	}

	@Override
	public Desempenho consultaStatus() {

		return null;
	}

	public Disciplina cadastroDisciplina() {

		Disciplina disciplina = new Disciplina();

		String nome = JOptionPane.showInputDialog("Digite o nome da disciplina: ");
		disciplina.setNome(nome);

		String cargah = JOptionPane.showInputDialog("Digite a carga horária dessa disciplina: ");
		double cargahoraria = Double.valueOf(cargah);
		disciplina.setCargahoraria(cargahoraria);

		String code = JOptionPane.showInputDialog("Cadastre um código de números para essa disciplina: ");
		long codigo = Long.valueOf(code);
		disciplina.setCodigo(codigo);

		JOptionPane.showMessageDialog(null,
				"A seguir aparecerão os professores disponíveis. Anote o ID (número) do respectivo professor para cadastrá-lo à disciplina: ");
		try {
			String listagemProfessor = "";
			for (Professor professor : professorservice.findAll())
				listagemProfessor += professor.getId() + " - " + professor.getNome() + " - "
						+ professor.getQualificacao() + " \n ";

			JOptionPane.showMessageDialog(null, professorservice.findAll());
			String option = JOptionPane.showInputDialog("Digite o ID do professor desejado para essa disciplina: ");
			int opcao = Integer.valueOf(option);
			Professor professor = professorservice.findById(opcao);
			disciplina.setProfessor(professor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int opt = 1;
		JOptionPane.showMessageDialog(null,
				"A seguir aparecerão os alunos disponíveis. Anote os ID's (números) dos alunos que deseja cadastrar à disciplina: ");
		do {
			try {
				String listagemAlunos = "";
				for (Aluno aluno : alunoservice.findAll())
					listagemAlunos += aluno.getId() + " - " + aluno.getNome() + " \n ";

				JOptionPane.showMessageDialog(null, listagemAlunos);
				Aluno aluno = cadAluno();
				List<Aluno> alunos = new ArrayList<>();
				alunos.add(aluno);
				disciplina.setAlunos(alunos);
				String op = JOptionPane
						.showInputDialog("1 = Cadastrar outro aluno || Qualquer outra tecla = Prosseguir");
				opt = Integer.valueOf(op);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Deu erro aqui cation!");
				e.printStackTrace();
			}
		} while (opt == 1);
		if (opt != 1) {
			String option = JOptionPane.showInputDialog("1. Menu Inicial || 5. Deslogar || 9. Encerrar aplicação");
			int opcao = Integer.valueOf(option);
			if (opcao == 1) {
				menu(null);
			}
			if (opcao == 5) {
				EntityManager em = Persistence.createEntityManagerFactory("teste01").createEntityManager();
				JOptionPane.showMessageDialog(null, "Voltando ao menu de login");
				LoginController login = new LoginControllerImpl(em);
				login.loginScreen();
			}
			if (opcao == 9) {
				JOptionPane.showMessageDialog(null, "Encerrando aplicação...");
				System.exit(0);
			}

		}
		return disciplina;
	}

	public Aluno cadAluno() {
		Aluno aluno = null;
		String opcao = JOptionPane.showInputDialog("Informe o ID do aluno: ");
		int id = Integer.valueOf(opcao);
		try {
			aluno = alunoservice.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aluno;
	}
}

// <---- Anotações ---->

// AlunoService alunoservice = new AlunoServiceImpl(entityManager);
// List<Aluno> alunos = new ArrayList<>();
// alunos.add(aluno);




// <---- Anotações ---->

// aluno.getDisciplinas().add(disciplina);




// <---- Anotações ---->

// entityManager.getTransaction().begin();
// aluno = entityManager.find(Aluno.class, aluno.setId(2));
// disciplina = entityManager.find(Disciplina.class,
// disciplina.setId(1));
// aluno.getDisciplinas().add(disciplina);
// disciplina.getAlunos().add(aluno);
// entityManager.getTransaction().commit();
// alunoservice.insert(aluno);
// disciplinaservice.insert(disciplina);
// disciplina.setAlunos(Arrays.asList(aluno));
// if (!aluno.getDisciplinas().isEmpty()) {b
// aluno.setDisciplinas(Arrays.asList(disciplina));
// } else {
// aluno.getDisciplinas().add(disciplina);



// <---- Anotações ---->

// aluno.getDisciplinas().add(disciplina);


// <---- Anotações ---->

// disciplina.setCargahoraria(cargahoraria);
// disciplina.setCodigo(codigo);
// disciplina.setNome(nome);
// disciplina.setProfessor(professor);
// disciplina.setAlunos(alunos);
// disciplina.setCurso(curso);
