package br.com.assertsistemas.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.dao.CursoDAO;
import br.com.assertsistemas.dao.DesempenhoDAO;
import br.com.assertsistemas.dao.DisciplinaDAO;
import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Status;
import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;

public class ManualInsert {

	public static void main(String[] args) throws Exception {

		// //Instance do entity
		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
		//
		// //Teste findMatricula
		// Aluno a = new AlunoDAOImpl(entityManager).findByMatricula(32913834);
		//
		// //Teste findUsuario
		// Aluno a = new AlunoDAOImpl(entityManager).findByUsuario(new
		// UsuarioDAOImpl(entityManager).findAll().get(0));
		//
		// //Teste findDisciplina
		// Aluno a = new AlunoDAOImpl(entityManager).findByDisciplina(new
		// DisciplinaDAOImpl(entityManager).findAll().get(0)).get(0);
		////
		// Aluno a = new AlunoDAOImpl(entityManager).findById(2);
		// System.out.println(a.getNome());

		AlunoDAO alunodao = new AlunoDAOImpl(entityManager);
		UsuarioDAO usuariodao = new UsuarioDAOImpl(entityManager);
		DisciplinaDAO disciplinadao = new DisciplinaDAOImpl(entityManager);
		CoordenadorDAO coordenadordao = new CoordenadorDAOImpl(entityManager);
		CursoDAO cursodao = new CursoDAOImpl(entityManager);
		ProfessorDAO professordao = new ProfessorDAOImpl(entityManager);
		DesempenhoDAO desempenhodao = new DesempenhoDAOImpl(entityManager);
		//
		//
		// System.out.println(a.getNome());

		//
		// <-------------------------- ALUNOS -------------------------->
		// Insert Aluno1
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		aluno.setNome("Marcos");
		aluno.setIdade(20);
		aluno.setSexo('M');
		aluno.setMatricula(32913834l);
		aluno.setSemestre(8);
		alunos.add(aluno);

		// // Insert Aluno
		// Aluno alunoA = new Aluno();
		// alunoA.setNome("Fernando");
		// alunoA.setIdade(10);
		// alunoA.setSexo('M');
		// alunoA.setMatricula(342134l);
		// alunoA.setSemestre(4);
		// alunos.add(alunoA);
		//
		// <-------------------------- USUÁRIOS -------------------------->
		// Insert Usuario
		Usuario usuarioA = new Usuario();
		usuarioA.setLogin("a");
		usuarioA.setSenha("a");
		usuarioA.setTipoUsuario(Tipo.ALUNO);
		aluno.setUsuario(usuarioA);
		usuariodao.insert(usuarioA);
		alunodao.insert(aluno);
		//
		Usuario usuarioB = new Usuario();
		usuarioB.setLogin("p");
		usuarioB.setSenha("p");
		usuarioB.setTipoUsuario(Tipo.PROFESSOR);
		usuariodao.insert(usuarioB);
		//
		//
		Usuario usuarioC = new Usuario();
		usuarioC.setLogin("c");
		usuarioC.setSenha("c");
		usuarioC.setTipoUsuario(Tipo.COORDENADOR);
		usuariodao.insert(usuarioC);

		// // Insert Usuario
		// Usuario usuarioD = new Usuario();
		// usuarioA.setLogin("fer");
		// usuarioA.setSenha("fer");
		// usuarioA.setTipoUsuario(Tipo.ALUNO);
		// alunoA.setUsuario(usuarioD);
		// usuariodao.insert(usuarioD);
		// alunodao.insert(alunoA);

		// Usuario usuarioE = new Usuario();
		// usuarioB.setLogin("ja");
		// usuarioB.setSenha("ja");
		// usuarioB.setTipoUsuario(Tipo.PROFESSOR);
		// usuariodao.insert(usuarioE);
		//
		// <-------------------------- DISCIPLINAS -------------------------->
		// Insert Disciplina
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Matemática");
		disciplina.setCargahoraria(80.0);
		disciplina.setCodigo(493872l);
		disciplina.setAlunos(alunos);
		disciplinadao.insert(disciplina);
		disciplinas.add(disciplina);
		aluno.setDisciplinas(disciplinas);
		alunodao.insert(aluno);

		// // Insert Disciplina
		// Disciplina disciplina1 = new Disciplina();
		// disciplina.setNome("Português");
		// disciplina.setCargahoraria(50.0);
		// disciplina.setCodigo(49532l);
		// disciplina.setAlunos(alunos);
		// disciplinadao.insert(disciplina1);
		// disciplinas.add(disciplina1);
		// aluno.setDisciplinas(disciplinas);
		// alunodao.insert(alunoA);
		//
		// <-------------------------- COORDENADORES -------------------------->
		// Insert Coordenador
		Coordenador coordenador = new Coordenador();
		coordenador.setNome("Escobar");
		coordenador.setIdade(48);
		coordenador.setSexo('M');
		coordenador.setUsuario(usuarioC);
		coordenadordao.insert(coordenador);
		//
		// <-------------------------- CURSOS -------------------------->
		// Insert Curso
		Curso curso = new Curso();
		curso.setCoordenador(coordenador);
		curso.setDisciplinas(disciplinas);
		curso.setNome("Sistemas de Informação");
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(curso);
		cursodao.insert(curso);
		disciplina.setCurso(cursos);
		//
		// <-------------------------- PROFESSORES -------------------------->
		// // Insert Professor
		Professor professor = new Professor();
		professor.setNome("Sérgio");
		professor.setIdade(45);
		professor.setSexo('M');
		professor.setQualificacao("Doutor");
		professor.setDisciplina(disciplinas);
		professor.setUsuario(usuarioB);
		professordao.insert(professor);
		disciplina.setProfessor(professor);

		// // Insert Professor
		// Professor professorA = new Professor();
		// professor.setNome("Janice");
		// professor.setIdade(30);
		// professor.setSexo('F');
		// professor.setQualificacao("Doutora");
		// professor.setDisciplina(disciplinas);
		// professor.setUsuario(usuarioE);
		// professordao.insert(professorA);
		// disciplina.setProfessor(professorA);

		// <-------------------------- DESEMPENHOS -------------------------->
		// Insert Desempenho
		Desempenho desempenho = new Desempenho();
		desempenho.setAluno(aluno);
		desempenho.setDisciplina(disciplina);
		desempenho.setNota1(5.5);
		desempenho.setNota2(6.5);
		desempenho.setNota3(9.0);
		desempenho.setStatus(Status.EM_ANDAMENTO);
		desempenhodao.insert(desempenho);

		//
		// Usuario usuarioC = new Usuario();
		// usuarioC.setLogin("");
		// usuarioC.setSenha("c");
		// usuarioC.setTipoUsuario(Tipo.COORDENADOR);
		// usuariodao.insert(usuarioC);

		// // Insert Coordenador
		// Coordenador coordenador = new Coordenador();
		// coordenador.setNome("Escobar");
		// coordenador.setIdade(48);
		// coordenador.setSexo('M');
		// coordenador.setUsuario(usuarioC);
		// coordenadordao.insert(coordenador);
		//
		//
		// // Insert Curso
		// Curso curso = new Curso();
		// curso.setCoordenador(coordenador);
		// curso.setDisciplinas(disciplinas);
		// curso.setNome("Sistemas de Informação");
		// List<Curso> cursos = new ArrayList<Curso>();
		// cursos.add(curso);
		// cursodao.insert(curso);
		// disciplina.setCurso(cursos);

		System.out.println(aluno.getNome());
	}

}
