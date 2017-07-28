package br.com.assertsistemas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Disciplina.findByCodigo", query = "SELECT d FROM Disciplina d WHERE d.codigo = :codigo"),
		@NamedQuery(name = "Disciplina.findByProfessor", query = "SELECT d FROM Disciplina d WHERE d.professor = :professor"),
		@NamedQuery(name = "Disciplina.findByCurso", query = "SELECT d FROM Disciplina as d JOIN FETCH d.cursos WHERE d.cursos = :curso"),
		@NamedQuery(name = "Disciplina.findByAluno", query = "SELECT d FROM Disciplina d WHERE d.alunos = :aluno")

})
public class Disciplina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private long codigo;

	private double cargahoraria;
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DISCIPLINA_CURSO", joinColumns = @JoinColumn(name = "disciplina_Id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "curso_Id", referencedColumnName = "id"))
	private List<Curso> cursos;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "DISCIPLINA_ALUNO", joinColumns = @JoinColumn(name = "disciplina_Id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "aluno_Id", referencedColumnName = "id", insertable = true, updatable = false))
	private List<Aluno> alunos;

	@ManyToOne
	private Professor professor;

	public Disciplina() {

	}

	public Disciplina(int id, long codigo, double cargahoraria, String nome, List<Curso> curso, List<Aluno> alunos,
			Professor professor) {

		this.id = id;
		this.codigo = codigo;
		this.cargahoraria = cargahoraria;
		this.nome = nome;
		this.cursos = curso;
		this.alunos = alunos;
		this.professor = professor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public double getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(double cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Curso> getCurso() {
		return cursos;
	}

	public void setCurso(List<Curso> curso) {
		this.cursos = curso;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", codigo=" + codigo + ", cargahoraria=" + cargahoraria + ", nome=" + nome
				+ ", curso=" + cursos + "]";
	}

}
