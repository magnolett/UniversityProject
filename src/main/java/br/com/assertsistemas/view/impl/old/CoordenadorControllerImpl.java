package br.com.assertsistemas.view.impl.old;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.CoordenadorService;
import br.com.assertsistemas.service.impl.CoordenadorServiceImpl;
import br.com.assertsistemas.view.AlunoController;
import br.com.assertsistemas.view.CoordenadorController;
import br.com.assertsistemas.view.ProfessorController;
import br.com.assertsistemas.view.impl.AlunoMenuControllerImpl;

public class CoordenadorControllerImpl implements CoordenadorController {

	private static final long serialVersionUID = 6137878294442315260L;
	private CoordenadorService coordenadorservice;
	private AlunoController alunocontroller;
	private ProfessorController professorcontroller;

	public CoordenadorControllerImpl(EntityManager entityManager) {
		this.coordenadorservice = new CoordenadorServiceImpl(entityManager);
		this.alunocontroller = new AlunoMenuControllerImpl();
		this.professorcontroller = new ProfessorControllerImpl();
	}

	public static void main(String[] args) throws Exception {

		AlunoController alunocontroller = new AlunoMenuControllerImpl();
		ProfessorController professorcontroller = new ProfessorControllerImpl();
		String option = JOptionPane.showInputDialog("Digite 1 para cadastrar aluno ou 2 para cadastrar professor");
		int a = Integer.valueOf(option);
		if (a == 1) {
			alunocontroller.cadastroAluno();
		} else if (a == 2) {
			professorcontroller.cadastroProfessor();
		}
	}

	public Coordenador findByUsuario(Usuario usuario) {
		return coordenadorservice.findByUsuario(usuario);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
