package br.com.assertsistemas.entity;

public enum Tipo {
	
	PROFESSOR,ALUNO,COORDENADOR;

	public static Tipo findTipo(String descricao){
		if(descricao != null && !descricao.isEmpty()){
			for (Tipo tipo : Tipo.values()) {
				if(tipo.name().equals(descricao)){
					return tipo;
				}
			}
		}
		return null;
	}
	
}