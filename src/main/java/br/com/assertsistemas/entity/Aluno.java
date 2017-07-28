package br.com.assertsistemas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Aluno.findByUsuario", query = "SELECT a FROM Aluno a WHERE a.usuario = :usuario"),
	@NamedQuery(name = "Aluno.findByMatricula", query = "SELECT a FROM Aluno a WHERE a.matricula = :matricula"),
	@NamedQuery(name = "Aluno.findByDisciplina", query = "SELECT a FROM Aluno as a JOIN FETCH a.disciplinas WHERE a.disciplinas = :disciplina")
	})

public class Aluno extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private long matricula;
	
	private int semestre;
	
	@ManyToMany (mappedBy = "alunos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Disciplina> disciplinas;
	
	public Aluno() {
		
	}
	
	public Aluno(long matricula, int semestre, List<Disciplina> disciplinas) {
		
		this.matricula = matricula;
		this.semestre = semestre;
		this.disciplinas = disciplinas;
	}

	public Aluno(long matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Aluno" + super.toString() + "[ matricula=" + matricula + ", semestre=" + semestre + ", disciplinas="
				+ disciplinas + "]";
	}

}
