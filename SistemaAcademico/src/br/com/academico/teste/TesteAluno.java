package br.com.academico.teste;

import br.com.academico.dao.AlunoDAO;
import br.com.academico.model.Aluno;

public class TesteAluno {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Aluno aluno = new Aluno();
			
			aluno.setPkRgm("1744324499");
			aluno.setFkPessoa(1);
			
			
			AlunoDAO dao = new AlunoDAO();
			
			dao.salvar(aluno);
			
			System.out.println("Aluno salvo com sucesso! ");
		} catch(Exception e) {
			System.out.println("Erro" + e.getLocalizedMessage());
		}

	}
	

}
