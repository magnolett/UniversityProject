package br.com.assertsistemas.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Curso.findByNome", query = "SELECT c FROM Curso c WHERE c.nome = :nome"),
		@NamedQuery(name = "Curso.findByCoordenador", query = "SELECT c FROM Curso c WHERE c.coordenador = :coordenador"),
		@NamedQuery(name = "Curso.findByDisciplina", query = "SELECT c FROM Curso as c JOIN FETCH c.disciplinas WHERE c.disciplinas = :disciplina") })

public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Coordenador coordenador;

	private String nome;

	@ManyToMany(mappedBy = "cursos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Disciplina> disciplinas;

	public Curso() {

	}

	public Curso(int id, Coordenador coordenador, String nome, List<Disciplina> disciplinas) {
		this.id = id;
		this.coordenador = coordenador;
		this.nome = nome;
		this.disciplinas = disciplinas;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso findByNome() {
		// TODO Auto-generated method stub
		return null;
	}

}
