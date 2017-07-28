package br.com.assertsistemas.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "Desempenho.findByAluno", query = "SELECT d FROM Desempenho d WHERE d.aluno = :aluno"),
		@NamedQuery(name = "Desempenho.findByDisciplina", query = "SELECT d FROM Desempenho d WHERE d.disciplina = :disciplina"),
		@NamedQuery(name = "Desempenho.findByAlunoAndDisciplina", query = "SELECT d FROM Desempenho d WHERE d.aluno = :aluno and d.disciplina = :disciplina"),
		@NamedQuery(name = "Desempenho.findByStatus", query = "SELECT d FROM Desempenho d WHERE d.status = :status")

})
public class Desempenho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double medianota;

	@OneToOne
	private Disciplina disciplina;

	private double nota1;

	private double nota2;

	private double nota3;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne
	private Aluno aluno;

	public Desempenho() {

	}

	public Desempenho(int id, double medianota, Disciplina disciplina, double nota1, double nota2, double nota3) {

		this.id = id;
		this.medianota = medianota;
		this.disciplina = disciplina;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMedianota() {
		return medianota;
	}

	public void setMedianota(double medianota) {
		this.medianota = medianota;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getNota3() {
		return nota3;
	}

	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}

// <---- Anotações ---->
// @NamedQuery (name = "Desempenho.findByStatusAndCoordenador", query = "SELECT
// d FROM Desempenho as d JOIN FETCH d.disciplina.curso.coordenador WHERE
// d.status = :status and d.disciplina.curso.coordenador = :coordenador")
