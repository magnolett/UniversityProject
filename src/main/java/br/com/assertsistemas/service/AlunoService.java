package br.com.assertsistemas.service;

import java.util.List;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Usuario;

public interface AlunoService extends GenericService<Aluno, Integer> {
	
	public Aluno findByUsuario (Usuario usuario);
	
	public Aluno findByMatricula(long matricula);
	
	public List<Aluno> findByDisciplina(Disciplina disciplina);

}
