//package br.com.assertsistemas.main;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//import javax.swing.JOptionPane;
//
//import br.com.assertsistemas.entity.Aluno;
//import br.com.assertsistemas.entity.Desempenho;
//import br.com.assertsistemas.service.DesempenhoService;
//import br.com.assertsistemas.service.impl.AlunoServiceImpl;
//import br.com.assertsistemas.service.impl.DesempenhoServiceImpl;
//
//public class MainAluno {
//
//	private DesempenhoService desempenhoservice;
//
//	public MainAluno(EntityManager entityManager) throws Exception {
//		desempenhoservice = new DesempenhoServiceImpl(entityManager);
//	}
//
//	public Aluno menuAluno(EntityManager entityManager, Aluno aluno) throws Exception {
//
//		String option = JOptionPane.showInputDialog("Digite 1 para consultar as notas OU 2 para calcular sua média");
//		int a = Integer.valueOf(option);
//
//		if (a == 1) {
//
//			List<Desempenho> desempenhos = desempenhoservice.findByAluno(aluno);
//
//			for (Desempenho desempenho : desempenhos) {
//				System.out.println(desempenho.getDisciplina().getNome());
//			}
//
//		} else if (a == 2) {
//			Desempenho desempenho = new Desempenho();
//			desempenhoservice.updateMediaNotas(desempenho);
//			
//			
//			
//
//		}
//		return aluno;
//	}
//
//	public static void main(String[] args) throws Exception {
//		EntityManager entityManager = Persistence.createEntityManagerFactory("teste01").createEntityManager();
//		MainAluno main = new MainAluno(entityManager);
//		main.menuAluno(entityManager, new AlunoServiceImpl(entityManager).findById(5));
//	}
//
//}
