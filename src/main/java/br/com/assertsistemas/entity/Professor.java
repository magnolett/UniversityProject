package br.com.assertsistemas.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "Professor.findByQualificacao", query = "SELECT p FROM Professor p WHERE p.qualificacao = :qualificacao"),
		@NamedQuery(name = "Professor.findByDisciplina", query = "SELECT p FROM Professor p WHERE p.disciplinas = :disciplina"),
		@NamedQuery(name = "Professor.findByUsuario", query = "SELECT p FROM Professor p WHERE p.usuario = :usuario")

})
public class Professor extends Pessoa {

	private String qualificacao;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Disciplina> disciplinas;

	public Professor() {

	}

	public Professor(String qualificacao, List<Disciplina> disciplinas) {
		this.qualificacao = qualificacao;
		this.disciplinas = disciplinas;

	}

	public String getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplina(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
