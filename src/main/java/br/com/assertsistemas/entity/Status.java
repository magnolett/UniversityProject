package br.com.assertsistemas.entity;

public enum Status {
	EM_ANDAMENTO("Em Andamento"), APROVADO("Aprovado"), PENDENTE("Pendente"), REPROVADO("Reprovado");
	private String nome;

	Status(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}